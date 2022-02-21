/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.watch;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.watch.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Get the recently released episodes.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getWatchRecentEpisodes">Jikan API docs - getWatchRecentEpisodes</a>
 */
public class WatchRecentEpisodesQuery extends PageableQuery<DataListHolderWithPagination<WatchEpisodes>, Flux<WatchEpisodes>, WatchRecentEpisodesQuery> {

	public WatchRecentEpisodesQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create().path("/watch/episodes");
	}

	@Override
	public Flux<WatchEpisodes> process(Mono<DataListHolderWithPagination<WatchEpisodes>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
