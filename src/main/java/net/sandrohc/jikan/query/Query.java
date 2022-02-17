/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query;

import java.io.*;
import java.net.URI;
import java.time.*;

import com.fasterxml.jackson.core.type.TypeReference;
import io.netty.handler.codec.http.HttpResponseStatus;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanResponseException;
import net.sandrohc.jikan.exception.JikanThrottleException;
import org.reactivestreams.Publisher;
import org.slf4j.*;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufMono;
import reactor.netty.http.client.HttpClientResponse;
import reactor.util.retry.Retry;

import static net.sandrohc.jikan.Jikan.JIKAN_MARKER;

/**
 * A base query.
 *
 * @param <T> the response type for this query - used to map the HTTP response
 * @param <R> the result type (can be either a mono or a flux)
 */
public abstract class Query<T, R extends Publisher<?>> {

	protected final Logger log;
	protected final Jikan jikan;


	public Query(Jikan jikan) {
		this.log = LoggerFactory.getLogger(getClass());
		this.jikan = jikan;
	}

	/**
	 * Build the query URL dynamically.
	 */
	public abstract QueryUrlBuilder getUrl();

	// TODO: see if it's possible to fetch type from Query constructor
	public abstract TypeReference<T> getResponseType();

	public R execute() throws JikanQueryException {
		URI uri = null;
		try {
			uri = getUrl().build();
			log.debug(JIKAN_MARKER, "Fetching request: {}", uri);

			final Mono<T> queryResults = jikan.httpClient.get().uri(uri)
					.responseSingle(this::extractBytesFromResponse)
					.retryWhen(Retry.backoff(jikan.maxRetries, Duration.ofMillis(500)).filter(th -> th instanceof JikanThrottleException))
					.flatMap(this::deserialize);

			return process(queryResults);
		} catch (Exception e) {
			throw new JikanQueryException("Error when executing query '" + getClass().getName() + "' with URI '" + uri + "'", e);
		}
	}

	/**
	 * Extract the results from the API response.
	 *
	 * @param content the stream with the initial API response
	 * @return a stream with the unwrapped objects
	 */
	@SuppressWarnings("unchecked")
	public R process(Mono<T> content) {
		return (R) content; // By default, return the original publisher
	}

	private Mono<byte[]> extractBytesFromResponse(HttpClientResponse res, ByteBufMono content) {
		log.trace(JIKAN_MARKER, "Received response for query '{}' and path '{}'", getClass(), res.path());

		if (res.status() == HttpResponseStatus.OK) {
			return content.asByteArray();
		} else if (res.status() == HttpResponseStatus.NOT_FOUND) {
			return Mono.empty();
		} else if (res.status() == HttpResponseStatus.TOO_MANY_REQUESTS) {
			return Mono.error(new JikanThrottleException());
		} else {
			return content.asString()
					.flatMap(str -> Mono.error(new JikanResponseException("Response returned error '" + res.status() +
							"' while executing query '" + getClass() + "' with URL '" + getUrl() + "': " + str)));
		}
	}

	public Mono<T> deserialize(byte[] content) {
		try {
			return Mono.just(jikan.objectMapper.readValue(content, getResponseType()));
		} catch (IOException e) {
			return Mono.error(jikan.dumpStacktrace(this, content, e));
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[url='" + getUrl() + "']";
	}
}
