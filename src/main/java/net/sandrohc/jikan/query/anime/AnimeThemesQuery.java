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
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the anime theme songs.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeThemes">Jikan API docs - getAnimeThemes</a>
 */
public class AnimeThemesQuery extends Query<DataHolder<AnimeThemes>, Mono<AnimeThemes>> {

	/** The anime ID. */
	protected final int id;

	public AnimeThemesQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/anime/" + id + "/themes");
	}

	@Override
	public TypeReference<DataHolder<AnimeThemes>> getResponseType() {
		return new TypeReference<DataHolder<AnimeThemes>>() { };
	}

	@Override
	public Mono<AnimeThemes> process(Mono<DataHolder<AnimeThemes>> content) {
		return content.map(results -> results.data);
	}
}
