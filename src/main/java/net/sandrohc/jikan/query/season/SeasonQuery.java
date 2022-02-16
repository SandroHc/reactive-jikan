/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.season;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.legacy.enums.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Get the anime released on a specific season.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getSeason">Jikan API docs - getSeason</a>
 */
public class SeasonQuery extends PageableQuery<DataListHolderWithPagination<Anime>, Flux<Anime>, SeasonQuery> {

	/** The season year. */
	protected final int year;

	/** The season. */
	protected final Season season;

	public SeasonQuery(Jikan jikan, int year, Season season) {
		super(jikan);
		this.year = year;
		this.season = season;
	}

	@Override
	public QueryUrl getInnerUrl() {
		return QueryUrl.endpoint("/seasons/" + year + '/' + season.getSearch());
	}

	@Override
	public TypeReference<DataListHolderWithPagination<Anime>> getResponseType() {
		return new TypeReference<DataListHolderWithPagination<Anime>>() { };
	}

	@Override
	public Flux<Anime> process(Mono<DataListHolderWithPagination<Anime>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
