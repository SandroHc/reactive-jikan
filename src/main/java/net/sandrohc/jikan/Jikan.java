/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
import java.util.*;
import java.util.stream.*;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import net.sandrohc.jikan.exception.JikanResponseException;
import net.sandrohc.jikan.exception.JikanThrottleException;
import net.sandrohc.jikan.factory.QueryFactory;
import net.sandrohc.jikan.query.Query;
import org.reactivestreams.Publisher;
import org.slf4j.*;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufMono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.client.HttpClientResponse;
import reactor.util.retry.Retry;

/**
 * The Jikan instance is responsible for executing queries and deserializing the response into POJOs.
 */
public class Jikan {

	private static final Logger LOG = LoggerFactory.getLogger(Jikan.class);

	public final boolean debug;
	public final String baseUrl;
	public final String userAgent;
	public final int maxRetries;

	public final HttpClient httpClient;
	public final ObjectMapper objectMapper;

	public Jikan() {
		this(new JikanBuilder()); // use builder defaults
	}

	public Jikan(JikanBuilder builder) {
		this.debug = builder.debug;
		this.baseUrl = builder.baseUrl;
		this.userAgent = builder.userAgent;
		this.maxRetries = builder.maxRetries;

		this.httpClient = HttpClient.create()
				.baseUrl(this.baseUrl)
				.headers(h -> h
						.add(HttpHeaderNames.ACCEPT, HttpHeaderValues.APPLICATION_JSON)
						.add(HttpHeaderNames.USER_AGENT, this.userAgent));

		this.objectMapper = new ObjectMapper()
				.registerModule(new JavaTimeModule())
				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	/**
	 * Build a query factory using this instance of Jikan.
	 *
	 * @return the query factory
	 */
	public QueryFactory query() {
		return new QueryFactory(this);
	}

	/**
	 * Executes the desired query and returns the parsed entity.
	 *
	 * @param query the query
	 * @param <INITIAL_TYPE> the expected entity type
	 * @return the parsed entity, or {@code null} if the entity was not found
	 */
	public <INITIAL_TYPE, PROCESSED_TYPE, P extends Publisher<PROCESSED_TYPE>> P query(Query<INITIAL_TYPE, PROCESSED_TYPE, P> query) {
		final String uri = buildUri(query);
		LOG.atDebug().addArgument(uri).log("Fetching request: {}");

		return query.process(httpClient.get().uri(uri).responseSingle(this::onResponse)
				.retryWhen(Retry.backoff(maxRetries, Duration.ofMillis(500)).filter(th -> th instanceof JikanThrottleException))
				.flatMap(query::deserialize));
	}

	private Mono<byte[]> onResponse(HttpClientResponse res, ByteBufMono content) {
		if (res.status() == HttpResponseStatus.OK) {
			return content.asByteArray();
		} else if (res.status() == HttpResponseStatus.NOT_FOUND) {
			return Mono.empty();
		} else if (res.status() == HttpResponseStatus.TOO_MANY_REQUESTS) {
			return Mono.error(new JikanThrottleException());
		} else {
			return content.asString().flatMap(str -> Mono.error(
					new JikanResponseException("Response returned error '" + res.status() + "' while executing query " + getClass().getSimpleName() + ": " + str)
			));
		}
	}

	private String buildUri(Query<?,?,?> query) {
		StringBuilder sb = new StringBuilder(query.getUri());

		Map<String, Object> queryParameters = query.getQueryParameters();
		if (!queryParameters.isEmpty()) {
			String params = queryParameters.entrySet().stream()
					.map(entry -> {
						final String key;
						final String value;
						if (entry.getValue() instanceof Collection) {
							key = entry.getKey() + "[]";
							value = ((Collection<?>) entry.getValue()).stream().map(String::valueOf).collect(Collectors.joining(","));
						} else {
							key = entry.getKey();
							value = String.valueOf(entry.getValue());
						}
						return key + "=" + value;
					})
					.collect(Collectors.joining("&"));

			sb.append('?').append(params);
		}

		return sb.toString();
	}

	public Exception dumpStacktrace(Query<?,?,?> query, byte[] response, Exception e) {
		if (!debug) {
			return new JikanResponseException("Error parsing JSON for query: " + query.getClass().getName(), e);
		}

		// formatter that prints: YYYYMMDDHH'T'MMSSNNN
		final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
				.appendValue(ChronoField.YEAR, 4)
				.appendValue(ChronoField.MONTH_OF_YEAR, 2)
				.appendValue(ChronoField.DAY_OF_MONTH, 2)
				.optionalStart()
				.appendLiteral('T')
				.appendValue(ChronoField.HOUR_OF_DAY, 2)
				.appendValue(ChronoField.MINUTE_OF_HOUR, 2)
				.optionalStart()
				.appendValue(ChronoField.SECOND_OF_MINUTE, 2)
				.optionalStart()
				.appendValue(ChronoField.MILLI_OF_SECOND, 3)
				.toFormatter();

		final String timestamp = LocalDateTime.now().format(formatter);

		Path reportPath = Paths.get("error-jikan-" + timestamp + ".log");

		try (BufferedWriter writer = Files.newBufferedWriter(reportPath, StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
			/* Query */
			writer.write("# Query");
			writer.newLine();
			writer.write("Query: " + query.getClass().getName());
			writer.newLine();
			writer.write("Class: " + query.getRequestClass().getName());
			writer.newLine();
			writer.write("URI: " + query.getUri());
			writer.newLine();
			writer.write("Query parameters: " + query.getQueryParameters());
			writer.newLine(); writer.newLine(); writer.flush();

			/* Exception */
			// get the exception with the entire stacktrace into a string
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));

			writer.write("# Exception");
			writer.newLine();
			writer.write(sw.toString());
			writer.newLine(); writer.newLine(); writer.flush();

			/* JSON response */
			writer.write("# Response");
			writer.newLine();
			writer.write(new String(response, StandardCharsets.UTF_8));
			writer.newLine(); writer.flush();
		} catch (IOException ex) {
			LOG.atError().setCause(ex).addArgument(query).addArgument(reportPath).log("Error dumping contents of {} to: {}");
		}

		return new JikanResponseException("Error parsing JSON for query: " + query.getClass().getName() +
				". A report file was generated at: " + reportPath, e);
	}


	/**
	 * A builder for the {@link Jikan} class.
	 */
	public static class JikanBuilder {

		private String baseUrl = "https://api.jikan.moe/v3";
		private String userAgent = getDefaultUserAgent();
		private boolean debug  = false;
		private int maxRetries = 3;

		public JikanBuilder() {
		}

		/**
		 * Defines the base URL for all the queries.
		 * <p>
		 * By default uses the official Jikan API endpoint.
		 *
		 * @param baseUrl the base URL
		 * @return the builder
		 */
		public JikanBuilder baseUrl(String baseUrl) {
			this.baseUrl = baseUrl;
			return this;
		}

		/**
		 * Defines the user agent for all the queries.
		 * <p>
		 * By default is: {@code reactive-jikan/<version>}
		 *
		 * @param userAgent the user agent
		 * @return the builder
		 */
		public JikanBuilder userAgent(String userAgent) {
			this.userAgent = userAgent;
			return this;
		}

		/**
		 * Defines the debug mode.
		 *
		 * @param debug the debug mode
		 * @return the builder
		 */
		public JikanBuilder debug(boolean debug) {
			this.debug = debug;
			return this;
		}

		/**
		 * Defines the amount of retries before failing the request.
		 *
		 * @param maxRetries the amount of retries
		 * @return the builder
		 */
		public JikanBuilder maxRetries(int maxRetries) {
			this.maxRetries = maxRetries;
			return this;
		}

		/**
		 * Build the Jikan instance.
		 *
		 * @return the Jikan instance
		 */
		public Jikan build() {
			return new Jikan(this);
		}

		/**
		 * Retrieves the user agent from the JAR manifest.
		 * <p>
		 * Usually is: {@code reactive-jikan/<version>}
		 *
		 * @return the default user agent
		 */
		public String getDefaultUserAgent() {
			Package pck = getClass().getPackage();
			if (pck.getImplementationTitle() == null) {
				return "reactive-jikan/development";
			} else {
				return pck.getImplementationTitle() + "/" + pck.getImplementationVersion();
			}
		}

	}

}
