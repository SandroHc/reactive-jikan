/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrl.endpoint;

/**
 * Query for the anime news.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeNews">Jikan API docs - getAnimeNews</a>
 */
public class AnimeNewsQuery extends Query<DataListHolderWithPagination<NewsArticle>, Flux<NewsArticle>> {

	/** The anime ID. */
	protected final int id;

	// TODO: validate javadoc by checking max news per page
	/** The page. Each page contains up to ? news. */
	protected final int page;

	public AnimeNewsQuery(Jikan jikan, int id, int page) throws JikanInvalidArgumentException {
		super(jikan);
		if (page < 1) throw new JikanInvalidArgumentException("page starts at index 1");

		this.id = id;
		this.page = page;
	}

	@Override
	public QueryUrl getUrl() {
		return endpoint("/anime/" + id + "/news")
				.param("page", page);
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
