/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the list of characters.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeCharacters">Jikan API docs - getAnimeCharacters</a>
 */
public class AnimeCharactersQuery extends Query<DataListHolder<AnimeCharacterDetails>, Flux<AnimeCharacterDetails>> {

	/** The anime ID. */
	private final int id;

	public AnimeCharactersQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUrl() {
		return endpoint("/anime/" + id + "/characters").build();
	}

	@Override
	public TypeReference<DataListHolder<AnimeCharacterDetails>> getResponseType() {
		return new TypeReference<DataListHolder<AnimeCharacterDetails>>() { };
	}

	@Override
	public Flux<AnimeCharacterDetails> process(Mono<DataListHolder<AnimeCharacterDetails>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
