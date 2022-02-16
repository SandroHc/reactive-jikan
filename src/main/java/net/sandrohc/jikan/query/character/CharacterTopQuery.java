/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.character;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.character.Character;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrl.endpoint;

/**
 * Get the most popular character by number of favorites.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getTopCharacters">Jikan API docs - getTopCharacters</a>
 */
public class CharacterTopQuery extends PageableQuery<DataListHolderWithPagination<Character>, Flux<Character>, CharacterTopQuery> {

	public CharacterTopQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public QueryUrl getInnerUrl() {
		return endpoint("/top/characters");
	}

	@Override
	public TypeReference<DataListHolderWithPagination<Character>> getResponseType() {
		return new TypeReference<DataListHolderWithPagination<Character>>() { };
	}

	@Override
	public Flux<Character> process(Mono<DataListHolderWithPagination<Character>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
