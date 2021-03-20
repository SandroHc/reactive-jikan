/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query;

import java.io.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;

import io.netty.handler.codec.http.HttpResponseStatus;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanResponseException;
import net.sandrohc.jikan.exception.JikanThrottleException;
import org.reactivestreams.Publisher;
import org.slf4j.*;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufMono;
import reactor.netty.http.client.HttpClientResponse;
import reactor.util.retry.Retry;

/**
 * A base query.
 *
 * @param <T> the result type for this query
 * @param <P> the publisher (mono or flux)
 */
public abstract class Query<T, P extends Publisher<T>> {

	protected final Logger log;
	protected final Jikan jikan;

	protected final Map<String, Object> queryParams;


	public Query(Jikan jikan) {
		this.log = LoggerFactory.getLogger(getClass());
		this.jikan = jikan;
		this.queryParams = new HashMap<>();
	}

	/**
	 * The query URI without parameters.
	 *
	 * @return the query URI
	 */
	public abstract String getUri();

	public Map<String, Object> getQueryParameters() {
		return Collections.unmodifiableMap(queryParams);
	}

	public abstract Class<T> getRequestClass();

	public Class<?> getInitialRequestClass() {
		return getRequestClass(); // By default, return the original class
	}

	public P execute() {
		final String uri = buildUriWithParams();
		log.atDebug().addArgument(uri).log("Fetching request: {}");

		final Mono<?> queryResults = jikan.httpClient.get().uri(uri).responseSingle(this::extractBytesFromResponse)
				.retryWhen(Retry.backoff(jikan.maxRetries, Duration.ofMillis(500)).filter(th -> th instanceof JikanThrottleException))
				.flatMap(this::deserialize);

		return process(queryResults);
	}

	/**
	 * Extract the results from the API response.
	 *
	 * @param content the stream with the initial API response
	 * @return a stream with the unwrapped objects
	 */
	@SuppressWarnings("unchecked")
	public P process(Mono<?> content) {
		return (P) content; // By default, return the original publisher
	}

	private String buildUriWithParams() {
		StringBuilder sb = new StringBuilder(getUri());

		Map<String, Object> queryParameters = getQueryParameters();
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

	private Mono<byte[]> extractBytesFromResponse(HttpClientResponse res, ByteBufMono content) {
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

	public Mono<?> deserialize(byte[] content) {
		try {
			return Mono.just(jikan.objectMapper.readValue(content, getInitialRequestClass()));
		} catch (IOException e) {
			return Mono.error(jikan.dumpStacktrace(this, content, e));
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[uri='" + getUri() + "']";
	}

}
