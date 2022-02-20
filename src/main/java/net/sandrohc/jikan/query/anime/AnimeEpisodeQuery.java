/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
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
 * Query for the anime episode details.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeEpisodeById">Jikan API docs - getAnimeEpisodeById</a>
 */
public class AnimeEpisodeQuery extends Query<DataHolder<AnimeEpisode>, Mono<AnimeEpisode>> {

	/** The anime ID. */
	protected final int id;

	/** The episode number. */
	protected final int episode;

	public AnimeEpisodeQuery(Jikan jikan, int id, int episode) {
		super(jikan);
		this.id = id;
		this.episode = episode;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/anime/" + id + "/episodes/" + episode);
	}

	@Override
	public Mono<AnimeEpisode> process(Mono<DataHolder<AnimeEpisode>> content) {
		return content.map(holder -> holder.data);
	}
}
