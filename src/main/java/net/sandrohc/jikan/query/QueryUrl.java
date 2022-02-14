/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query;

import java.util.*;
import java.util.function.*;

import org.slf4j.*;

import static net.sandrohc.jikan.Jikan.JIKAN_MARKER;

/**
 * A query URL builder, with support to query parameters.
 */
public class QueryUrl {

	private static final Logger log = LoggerFactory.getLogger(QueryUrl.class);

	protected final StringBuilder builder;
	protected boolean hasQueryParameters = false;


	private QueryUrl(String endpoint) {
		Objects.requireNonNull(endpoint);
		this.builder = new StringBuilder(endpoint);
	}

	public static QueryUrl endpoint(String endpoint) {
		return new QueryUrl(endpoint);
	}

	public <T> QueryUrl param(String key, T value, Function<T, Object> mapper) {
		return param(key, value != null ? mapper.apply(value) : null);
	}

	public QueryUrl param(String key, Object value) {
		if (value == null) {
			log.trace(JIKAN_MARKER, "Ignoring null query parameter '{}'", key);
			return this;
		}

		log.trace(JIKAN_MARKER, "Appending query parameter '{}': {}", key, value);

		builder.append(hasQueryParameters ? '&' : '?');
		hasQueryParameters = true;

		// TODO: support arrays
		// TODO: url-encode key and value
		if (value instanceof Collection) {
			builder.append(key).append("[]=");

			boolean isFirstValue = true;
			for (Object val : ((Collection<?>) value)) {
				if (isFirstValue) {
					isFirstValue = false;
				} else {
					builder.append(',');
				}

				builder.append(val);
			}
		} else {
			builder.append(key).append('=').append(value);
		}

		return this;
	}

	public String build() {
		return builder.toString();
	}
}
