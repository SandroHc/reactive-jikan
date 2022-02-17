/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the manga reviews.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMangaReviews">Jikan API docs - getMangaReviews</a>
 */
public class MangaReviewsQuery extends PageableQuery<DataListHolderWithPagination<Review>, Flux<Review>, MangaReviewsQuery> {

	/** The manga ID. */
	private final int id;

	public MangaReviewsQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return endpoint("/manga/" + id + "/reviews");
	}

	@Override
	public TypeReference<DataListHolderWithPagination<Review>> getResponseType() {
		return new TypeReference<DataListHolderWithPagination<Review>>() { };
	}

	@Override
	public Flux<Review> process(Mono<DataListHolderWithPagination<Review>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
