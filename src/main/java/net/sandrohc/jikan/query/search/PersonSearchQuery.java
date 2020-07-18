/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.search;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.person.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonSearchQuery extends SearchQuery<PersonSearchQuery, PersonSearch, PersonSub> {

	public PersonSearchQuery(Jikan jikan) {
		super(jikan, Type.PERSON);
	}

	@Override
	public Class<PersonSearch> getRequestClass() {
		return PersonSearch.class;
	}

	@Override
	public Flux<PersonSub> process(Mono<PersonSearch> content) {
		return content.flatMapMany(search -> Flux.fromIterable(search.results));
	}

}
