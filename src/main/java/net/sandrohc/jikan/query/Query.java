/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query;

import java.util.*;
import java.util.stream.*;

import net.sandrohc.jikan.Jikan;
import reactor.core.publisher.Mono;

public abstract class Query<T> {

	protected final Jikan jikan;
	protected final Map<String, Object> queryParams;

	public Query(Jikan jikan) {
		this.jikan = jikan;
		this.queryParams = new HashMap<>();
	}

	public Mono<T> execute() {
		return jikan.query(this);
	}

	public abstract Class<T> getRequestClass();

	protected abstract String getBaseUri();

	public String getUri() {
		StringBuilder sb = new StringBuilder(getBaseUri());

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
