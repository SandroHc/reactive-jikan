/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query;

import java.util.*;


public class QueryUrlBuilder {

	private final StringBuilder builder;
	private boolean hasQueryParameters = false;


	private QueryUrlBuilder(String endpoint) {
		Objects.requireNonNull(endpoint);
		this.builder = new StringBuilder(endpoint);
	}

	public static QueryUrlBuilder endpoint(String endpoint) {
		return new QueryUrlBuilder(endpoint);
	}

	public QueryUrlBuilder queryParam(String key, Object value) {
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
