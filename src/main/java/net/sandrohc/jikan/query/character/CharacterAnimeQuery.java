/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.character;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.character.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the character appearances on anime.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getCharacterAnime">Jikan API docs - getCharacterAnime</a>
 */
public class CharacterAnimeQuery extends Query<DataListHolder<CharacterAnime>, Flux<CharacterAnime>> {

	/** The character ID. */
	private final int id;

	public CharacterAnimeQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUrl() {
		return endpoint("/characters/" + id + "/anime").build();
	}

	@Override
	public TypeReference<DataListHolder<CharacterAnime>> getResponseType() {
		return new TypeReference<DataListHolder<CharacterAnime>>() { };
	}

	@Override
	public Flux<CharacterAnime> process(Mono<DataListHolder<CharacterAnime>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
