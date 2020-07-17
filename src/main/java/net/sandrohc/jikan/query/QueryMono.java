/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query;

import java.io.IOException;

import net.sandrohc.jikan.Jikan;
import reactor.core.publisher.Mono;

public abstract class QueryMono<T> extends Query<T, Mono<T>> {

	public QueryMono(Jikan jikan) {
		super(jikan);
	}

	@Override
	public Mono<T> prepareResponse(Mono<byte[]> content) {
		return content.flatMap(this::deserialize);
	}

	@Override
	public Mono<T> deserialize(byte[] content) {
		try {
			return Mono.just(jikan.objectMapper.readValue(content, getRequestClass()));
		} catch (IOException e) {
			return Mono.error(jikan.dumpStacktrace(this, content, e));
		}
	}

}
