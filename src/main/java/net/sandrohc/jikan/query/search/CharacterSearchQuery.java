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

public class CharacterSearchQuery extends SearchQuery<CharacterSearchQuery, CharacterSearch, CharacterSub> {

	public CharacterSearchQuery(Jikan jikan) {
		super(jikan, Type.CHARACTER);
	}

	@Override
	public Class<CharacterSearch> getRequestClass() {
		return CharacterSearch.class;
	}

	@Override
	public Flux<CharacterSub> process(Mono<CharacterSearch> content) {
		return content.flatMapMany(search -> Flux.fromIterable(search.results));
	}

}
