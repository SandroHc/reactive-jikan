/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.search;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.legacy.person.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonSearchQuery extends SearchQuery<PersonSearchQuery, PersonSearchSub> {

	public PersonSearchQuery(Jikan jikan) {
		super(jikan, Type.PERSON);
	}

	@Override
	public Class<PersonSearchSub> getRequestClass() {
		return PersonSearchSub.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return PersonSearch.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<PersonSearchSub> process(Mono<?> content) {
		return ((Mono<PersonSearch>) content).flatMapMany(search -> Flux.fromIterable(search.results));
	}

}
