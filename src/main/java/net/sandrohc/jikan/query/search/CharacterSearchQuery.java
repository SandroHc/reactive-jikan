/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.search;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.character.*;
import net.sandrohc.jikan.model.enums.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CharacterSearchQuery extends SearchQuery<CharacterSearchQuery, CharacterSearchSub> {

	public CharacterSearchQuery(Jikan jikan) {
		super(jikan, Type.CHARACTER);
	}

	@Override
	public Class<CharacterSearchSub> getRequestClass() {
		return CharacterSearchSub.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return CharacterSearch.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<CharacterSearchSub> process(Mono<?> content) {
		return ((Mono<CharacterSearch>) content).flatMapMany(search -> Flux.fromIterable(search.results));
	}

}
