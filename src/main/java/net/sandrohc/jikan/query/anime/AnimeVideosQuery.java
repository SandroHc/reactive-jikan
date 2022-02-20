/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the anime videos.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeVideos">Jikan API docs - getAnimeVideos</a>
 */
public class AnimeVideosQuery extends Query<DataHolder<AnimeVideos>, Mono<AnimeVideos>> {

	/** The anime ID. */
	protected final int id;

	public AnimeVideosQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/anime/" + id + "/videos");
	}

	@Override
	public Mono<AnimeVideos> process(Mono<DataHolder<AnimeVideos>> content) {
		return content.map(results -> results.data);
	}
}
