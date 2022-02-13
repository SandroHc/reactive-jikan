/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
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
 * Query for the anime external links.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeExternal">Jikan API docs - getAnimeExternal</a>
 */
public class AnimeExternalQuery extends Query<DataListHolder<AnimeExternal>, Flux<AnimeExternal>> {

	/** The anime ID. */
	private final int id;

	public AnimeExternalQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUrl() {
		return endpoint("/anime/" + id + "/external").build();
	}

	@Override
	public TypeReference<DataListHolder<AnimeExternal>> getResponseType() {
		return new TypeReference<DataListHolder<AnimeExternal>>() { };
	}

	@Override
	public Flux<AnimeExternal> process(Mono<DataListHolder<AnimeExternal>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
