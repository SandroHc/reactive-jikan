/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.character.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the list of characters.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeCharacters">Jikan API docs - getAnimeCharacters</a>
 */
public class AnimeCharactersQuery extends Query<DataListHolder<CharacterBasic>, Flux<CharacterBasic>> {

	/** The anime ID. */
	protected final int id;

	public AnimeCharactersQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/anime/" + id + "/characters");
	}

	@Override
	public Flux<CharacterBasic> process(Mono<DataListHolder<CharacterBasic>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
