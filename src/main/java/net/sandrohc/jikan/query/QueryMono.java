/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query;

import net.sandrohc.jikan.Jikan;
import reactor.core.publisher.Mono;

/**
 * A query that returns a single element.
 *
 * @param <TYPE> the request result type
 */
public abstract class QueryMono<TYPE> extends Query<TYPE, TYPE, Mono<TYPE>> {

	public QueryMono(Jikan jikan) {
		super(jikan);
	}

	public Mono<TYPE> process(Mono<TYPE> content) {
		return content; // NO-OP
	}

}
