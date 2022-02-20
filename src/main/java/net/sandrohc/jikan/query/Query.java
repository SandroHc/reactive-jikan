/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.*;
import java.time.temporal.*;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanResponseException;
import net.sandrohc.jikan.exception.JikanThrottleException;
import org.reactivestreams.Publisher;
import org.slf4j.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;
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

	private static final String CACHE_EXPIRE_HEADER = HttpHeaderNames.EXPIRES.toString();
	private static final TemporalAmount CACHE_EXPIRE_DEFAULT = Period.ofDays(1);

	protected final Logger log;
	public final Jikan jikan;
	public final TypeReference<T> responseType;

	public Query(Jikan jikan) {
		this.log = LoggerFactory.getLogger(getClass());
		this.jikan = jikan;

		// Use the first generic type as the response class type.
		Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.responseType = new JikanTypeReference(type);
	}

	/**
	 * Build the query URL dynamically.
	 */
	public abstract QueryUrlBuilder getUrl();

	public R execute() throws JikanQueryException {
		String url = null;
		try {
			url = getUrl().build();
			log.debug(JIKAN_MARKER, "Fetching request: {}", url);

			final Mono<T> response;

			final Optional<Object> cachedResults = jikan.cache.get(url);
			if (cachedResults.isPresent()) {
				log.trace(JIKAN_MARKER, "Found in cache: {}", url);

				@SuppressWarnings("unchecked")
				final Mono<Signal<T>> results = ((Mono<Signal<T>>) cachedResults.get());

				response = results.dematerialize();
			} else {
				log.trace(JIKAN_MARKER, "Not found in cache: {}", url);
				final String finalUrl = url;

				response = jikan.httpClient.get().uri(url)
						.responseSingle(this::extractResponse)
						.retryWhen(Retry.backoff(jikan.maxRetries, Duration.ofMillis(500)).filter(th -> th instanceof JikanThrottleException))
						.flatMap(holder -> {
							// Store in the cache
							jikan.cache.put(finalUrl, holder.result.materialize(), holder.expires);
							return holder.result;
						});
			}

			return process(response);
		} catch (Exception e) {
			throw new JikanQueryException("Error when executing query '" + getClass().getName() + "' with URL '" + url + "'", e);
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

	private Mono<ResponseHolder> extractResponse(HttpClientResponse res, ByteBufMono content) {
		log.trace(JIKAN_MARKER, "Received response for query '{}' and path '{}'", getClass(), res.path());

		if (res.status() == HttpResponseStatus.OK) {
			return content.asByteArray().map(bytes -> new ResponseHolder(res, deserialize(bytes)));
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
			return Mono.just(jikan.objectMapper.readValue(content, responseType));
		} catch (IOException e) {
			return Mono.error(jikan.dumpStacktrace(this, content, e));
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[url='" + getUrl() + "']";
	}


	private class JikanTypeReference extends TypeReference<T> {
		protected Type type;

		public JikanTypeReference(Type type) {
			this.type = type;
		}

		@Override
		public Type getType() {
			return type;
		}
	}

	private class ResponseHolder {
		/** The deserialized result. */
		public final Mono<T> result;

		/** The expiration date. */
		public final OffsetDateTime expires;

		public ResponseHolder(HttpClientResponse response, Mono<T> result) {
			this.result = result;

			long expiresEpoch = response.responseHeaders().getInt(CACHE_EXPIRE_HEADER, -1);
			if (expiresEpoch < 0) {
				this.expires = OffsetDateTime.now().plus(CACHE_EXPIRE_DEFAULT);
			} else {
				this.expires = OffsetDateTime.of(LocalDateTime.ofEpochSecond(expiresEpoch, 0, ZoneOffset.UTC), ZoneOffset.UTC);
			}
		}
	}
}
