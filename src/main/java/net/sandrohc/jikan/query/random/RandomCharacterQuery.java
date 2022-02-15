/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.random;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.character.Character;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrl;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrl.endpoint;

/**
 * Query for a random character.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getRandomCharacters">Jikan API docs - getRandomCharacters</a>
 */
public class RandomCharacterQuery extends Query<DataHolder<Character>, Mono<Character>> {

	public RandomCharacterQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public QueryUrl getUrl() {
		return endpoint("/random/characters");
	}

	@Override
	public TypeReference<DataHolder<Character>> getResponseType() {
		return new TypeReference<DataHolder<Character>>() { };
	}

	@Override
	public Mono<Character> process(Mono<DataHolder<Character>> content) {
		return content.map(holder -> holder.data);
	}
}
