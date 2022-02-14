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
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the character details.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getCharacterById">Jikan API docs - getCharacterById</a>
 */
public class CharacterQuery extends Query<DataHolder<Character>, Mono<Character>> {

	/** The character ID. */
	protected final int id;

	public CharacterQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUrl() {
		return endpoint("/characters/" + id).build();
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
