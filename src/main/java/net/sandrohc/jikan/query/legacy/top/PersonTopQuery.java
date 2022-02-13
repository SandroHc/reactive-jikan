/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.top;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.legacy.person.PersonTop;
import net.sandrohc.jikan.query.legacy.person.PersonTopSub;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonTopQuery extends TopQuery<PersonTopSub> {

	public PersonTopQuery(Jikan jikan, int page) throws JikanInvalidArgumentException {
		super(jikan, Type.PERSON, page);
	}

	@Override
	public Class<PersonTopSub> getRequestClass() {
		return PersonTopSub.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return PersonTop.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<PersonTopSub> process(Mono<?> content) {
		return ((Mono<PersonTop>) content).flatMapMany(results -> Flux.fromIterable(results.top));
	}

}
