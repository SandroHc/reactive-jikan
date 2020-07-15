/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query;

import java.util.*;

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

	public abstract String getUri();

	public Map<String, Object> getQueryParameters() {
		return Collections.unmodifiableMap(queryParams);
	}

	public abstract Class<T> getRequestClass();

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[uri='" + getUri() + "']";
	}

}
