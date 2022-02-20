/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
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
 * Query for the anime reviews.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeReviews">Jikan API docs - getAnimeReviews</a>
 */
public class AnimeReviewsQuery extends PageableQuery<DataListHolderWithPagination<Review>, Flux<Review>, AnimeReviewsQuery> {

	/** The anime ID. */
	protected final int id;

	public AnimeReviewsQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create()
				.path("/anime/" + id + "/reviews")
				.param("page", page);
	}

	@Override
	public Flux<Review> process(Mono<DataListHolderWithPagination<Review>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
