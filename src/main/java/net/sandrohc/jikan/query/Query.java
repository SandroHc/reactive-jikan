/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query;

import java.util.*;
import java.util.stream.*;

public interface Query<T> {

	Map<String, Object> queryParams = new HashMap<>();


	Class<T> getRequestClass();

	String getBaseUri();

	default String getUri() {
		StringBuilder sb = new StringBuilder();

		sb.append(getBaseUri());

		if (!queryParams.isEmpty()) {
			String params = this.queryParams.entrySet().stream()
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

}
