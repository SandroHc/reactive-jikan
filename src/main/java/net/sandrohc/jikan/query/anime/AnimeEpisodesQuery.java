/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the list of episodes.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeEpisodes">Jikan API docs - getAnimeEpisodes</a>
 */
public class AnimeEpisodesQuery extends PageableQuery<DataListHolderWithPagination<AnimeEpisode>, Flux<AnimeEpisode>, AnimeEpisodesQuery> {

	/** The anime ID. */
	protected final int id;

	public AnimeEpisodesQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create().path("/anime/" + id + "/episodes");
	}

	@Override
	public Flux<AnimeEpisode> process(Mono<DataListHolderWithPagination<AnimeEpisode>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
