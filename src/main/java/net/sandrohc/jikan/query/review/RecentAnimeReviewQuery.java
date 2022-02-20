/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.review;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for a list of the latest anime reviews.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getRecentAnimeReviews">Jikan API docs - getRecentAnimeReviews</a>
 */
public class RecentAnimeReviewQuery extends PageableQuery<DataListHolderWithPagination<Review>, Flux<Review>, RecentAnimeReviewQuery> {

	public RecentAnimeReviewQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create().path("/reviews/anime");
	}

	@Override
	public Flux<Review> process(Mono<DataListHolderWithPagination<Review>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
