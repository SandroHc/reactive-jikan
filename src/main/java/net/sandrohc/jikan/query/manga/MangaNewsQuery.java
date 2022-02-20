/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the manga news.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMangaNews">Jikan API docs - getMangaNews</a>
 */
public class MangaNewsQuery extends PageableQuery<DataListHolderWithPagination<NewsArticle>, Flux<NewsArticle>, MangaNewsQuery> {

	/** The manga ID. */
	private final int id;

	public MangaNewsQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create().path("/manga/" + id + "/news");
	}

	@Override
	public Flux<NewsArticle> process(Mono<DataListHolderWithPagination<NewsArticle>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
