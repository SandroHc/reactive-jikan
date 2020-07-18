/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.top;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.person.PersonTop;
import net.sandrohc.jikan.query.person.PersonTopSub;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonTopQuery extends TopQuery<PersonTopQuery, PersonTop, PersonTopSub> {

	public PersonTopQuery(Jikan jikan, int page) throws JikanInvalidArgumentException {
		super(jikan, Type.PERSON, page);
	}

	@Override
	public Class<PersonTop> getRequestClass() {
		return PersonTop.class;
	}

	@Override
	public Flux<PersonTopSub> process(Mono<PersonTop> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.top));
	}

}
