/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query;

import java.io.IOException;

import net.sandrohc.jikan.Jikan;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class QueryFlux<T> extends Query<T, Flux<T>> {

	public QueryFlux(Jikan jikan) {
		super(jikan);
	}

	public abstract String getRootElement();

	@Override
	public Flux<T> prepareResponse(Mono<byte[]> content) {
		return content.flatMapMany(this::deserialize);
	}

	@Override
	protected Flux<T> deserialize(byte[] content) {
		try {
			return Flux.just(jikan.objectMapper.readValue(content, getRequestClass()));
		} catch (IOException e) {
			return Flux.error(jikan.dumpStacktrace(this, content, e));
		}
	}

	protected <R> Mono<R> executeAsMono(Class<R> rootType) {
		return jikan.query(new QueryMono<R>(QueryFlux.this.jikan) {
			@Override
			public String getUri() {
				return QueryFlux.this.getUri();
			}

			@Override
			public Class<R> getRequestClass() {
				return rootType;
			}
		});
	}

}
