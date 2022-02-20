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
 * Query for a list of the latest manga reviews.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getRecentMangaReviews">Jikan API docs - getRecentMangaReviews</a>
 */
public class RecentMangaReviewQuery extends PageableQuery<DataListHolderWithPagination<Review>, Flux<Review>, RecentMangaReviewQuery> {

	public RecentMangaReviewQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create().path("/reviews/manga");
	}

	@Override
	public Flux<Review> process(Mono<DataListHolderWithPagination<Review>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
