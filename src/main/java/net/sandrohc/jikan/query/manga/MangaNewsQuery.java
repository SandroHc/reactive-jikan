/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the manga news.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMangaNews">Jikan API docs - getMangaNews</a>
 */
public class MangaNewsQuery extends Query<DataListHolderWithPagination<NewsArticle>, Flux<NewsArticle>> {

	/** The manga ID. */
	private final int id;

	/** The page number. */
	private final int page;

	public MangaNewsQuery(Jikan jikan, int id, int page) throws JikanInvalidArgumentException {
		super(jikan);
		if (page < 1) throw new JikanInvalidArgumentException("page starts at index 1");

		this.id = id;
		this.page = page;
	}

	@Override
	public String getUrl() {
		return endpoint("/manga/" + id + "/news")
				.queryParam("page", page)
				.build();
	}

	@Override
	public TypeReference<DataListHolderWithPagination<NewsArticle>> getResponseType() {
		return new TypeReference<DataListHolderWithPagination<NewsArticle>>() { };
	}

	@Override
	public Flux<NewsArticle> process(Mono<DataListHolderWithPagination<NewsArticle>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
