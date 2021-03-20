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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import net.sandrohc.jikan.exception.JikanResponseException;
import net.sandrohc.jikan.factory.QueryFactory;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.utils.Generated;
import org.reactivestreams.Publisher;
import org.slf4j.*;
import reactor.netty.http.client.HttpClient;

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
				.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
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
	 * @param <INITIAL_TYPE> the request entity type
	 * @param <PROCESSED_TYPE> the processed entity type
	 * @param <P> the publisher type (Mono or Flux)
	 * @return the parsed entity, or {@code null} if the entity was not found
	 */
	public <T, P extends Publisher<T>> P query(Query<T, P> query) {
		return query.execute();
	}

	public Exception dumpStacktrace(Query<?,?> query, byte[] response, Exception e) {
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
		@Generated
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
