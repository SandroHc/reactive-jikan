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
import net.sandrohc.jikan.factory.QueryFactory;
import net.sandrohc.jikan.query.Query;
import org.slf4j.*;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

public class Jikan {

	private static final Logger LOG = LoggerFactory.getLogger(Jikan.class);

	private static final ObjectMapper JSON_PARSER = new ObjectMapper()
			.registerModule(new JavaTimeModule())
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

	private final HttpClient httpClient;
	private final boolean debug;

	public Jikan() {
		this(new JikanBuilder()); // use builder defaults
	}

	public Jikan(JikanBuilder builder) {
		this.debug   = builder.debug;

		this.httpClient = HttpClient.create()
				.baseUrl(builder.baseUrl)
				.headers(h -> h
						.add(HttpHeaderNames.ACCEPT, HttpHeaderValues.APPLICATION_JSON)
						.add(HttpHeaderNames.USER_AGENT, builder.userAgent));
	}

	public QueryFactory query() {
		return new QueryFactory(this);
	}

	public <T> Mono<T> query(Query<T> query) {
		final String uri = query.getUri();
		LOG.atDebug().addArgument(uri).log("Fetching request: {}");

		return httpClient.get().uri(uri)
				.responseContent()
				.aggregate()
				.asByteArray()
				.flatMap(response -> decode(query, response));
	}

	private <T> Mono<T> decode(Query<T> query, byte[] response) {
		try {
			return Mono.just(JSON_PARSER.readValue(response, query.getRequestClass()));
		} catch (IOException e) {
			return Mono.error(dumpStacktrace(query, response, e));
		}
	}

	private <T> Exception dumpStacktrace(Query<T> query, byte[] response, Exception e) {
		if (!debug) {
			return new JikanException("Error parsing JSON for query: " + query.getClass().getName(), e);
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

		return new JikanException("Error parsing JSON for query: " + query.getClass().getName() +
				". A report file was generated at: " + reportPath, e);
	}


	public static class JikanBuilder {

		private String baseUrl = "https://api.jikan.moe/v3";
		private String userAgent = getDefaultUserAgent();
		private boolean debug  = false;

		public JikanBuilder() {
		}

		public JikanBuilder baseUrl(String baseUrl) {
			this.baseUrl = baseUrl;
			return this;
		}

		public JikanBuilder userAgent(String userAgent) {
			this.userAgent = userAgent;
			return this;
		}

		public JikanBuilder debug(boolean debug) {
			this.debug = debug;
			return this;
		}

		public Jikan build() {
			return new Jikan(this);
		}

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
