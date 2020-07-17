/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query;

import java.io.*;
import java.util.*;

import net.sandrohc.jikan.Jikan;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public abstract class Query<TYPE_INITIAL, TYPE_FINAL, PUBLISHER extends Publisher<TYPE_FINAL>> {

	protected final Jikan jikan;
	protected final Map<String, Object> queryParams;

	public Query(Jikan jikan) {
		this.jikan = jikan;
		this.queryParams = new HashMap<>();
	}

	public abstract String getUri();

	public Map<String, Object> getQueryParameters() {
		return Collections.unmodifiableMap(queryParams);
	}

	public abstract Class<TYPE_INITIAL> getRequestClass();


	public PUBLISHER execute() {
		return jikan.query(this);
	}

	public Mono<TYPE_INITIAL> deserialize(byte[] content) {
		try {
			return Mono.just(jikan.objectMapper.readValue(content, getRequestClass()));
		} catch (IOException e) {
			return Mono.error(jikan.dumpStacktrace(this, content, e));
		}
	}

	public abstract PUBLISHER process(Mono<TYPE_INITIAL> content);

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[uri='" + getUri() + "']";
	}

}
