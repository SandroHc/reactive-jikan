/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the anime news.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeNews">Jikan API docs - getAnimeNews</a>
 */
public class AnimeNewsQuery extends PageableQuery<DataListHolderWithPagination<NewsArticle>, Flux<NewsArticle>, AnimeNewsQuery> {

	/** The anime ID. */
	protected final int id;

	public AnimeNewsQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create().path("/anime/" + id + "/news");
	}

	@Override
	public Flux<NewsArticle> process(Mono<DataListHolderWithPagination<NewsArticle>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
