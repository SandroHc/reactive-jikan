/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.season;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Get the anime released on the current season.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getSeasonNow">Jikan API docs - getSeasonNow</a>
 */
public class SeasonNowQuery extends PageableQuery<DataListHolderWithPagination<Anime>, Flux<Anime>, SeasonNowQuery> {

	public SeasonNowQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create().path("/seasons/now");
	}

	@Override
	public Flux<Anime> process(Mono<DataListHolderWithPagination<Anime>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
