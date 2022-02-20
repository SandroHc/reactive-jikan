/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.season;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.season.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Get the list of available anime seasons, by year and season.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getSeasonsList">Jikan API docs - getSeasonsList</a>
 */
public class SeasonListQuery extends PageableQuery<DataListHolderWithPagination<SeasonEntry>, Flux<SeasonEntry>, SeasonListQuery> {

	public SeasonListQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create().path("/seasons");
	}

	@Override
	public Flux<SeasonEntry> process(Mono<DataListHolderWithPagination<SeasonEntry>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
