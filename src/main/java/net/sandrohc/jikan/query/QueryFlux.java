/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query;

import net.sandrohc.jikan.Jikan;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class QueryFlux<TYPE_INITIAL, TYPE_FINAL> extends Query<TYPE_INITIAL, TYPE_FINAL, Flux<TYPE_FINAL>> {

	public QueryFlux(Jikan jikan) {
		super(jikan);
	}

	public abstract Flux<TYPE_FINAL> process(Mono<TYPE_INITIAL> content);

}
