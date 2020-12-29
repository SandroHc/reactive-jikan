/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query;

import net.sandrohc.jikan.Jikan;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * A query that returns a list of element.
 *
 * @param <TYPE_INITIAL> the result type
 * @param <TYPE_FINAL> the final type
 */
public abstract class QueryFlux<TYPE_INITIAL, TYPE_FINAL> extends Query<TYPE_INITIAL, TYPE_FINAL, Flux<TYPE_FINAL>> {

	public QueryFlux(Jikan jikan) {
		super(jikan);
	}

	public abstract Flux<TYPE_FINAL> process(Mono<TYPE_INITIAL> content);

}
