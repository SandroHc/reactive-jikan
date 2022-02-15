/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.review;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrl.endpoint;

/**
 * Query for a list of the latest anime reviews.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getRecentAnimeReviews">Jikan API docs - getRecentAnimeReviews</a>
 */
public class RecentAnimeReviewQuery extends PageableQuery<DataListHolderWithPagination<Recommendation>, Flux<Recommendation>, RecentAnimeReviewQuery> {

	public RecentAnimeReviewQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public QueryUrl getInnerUrl() {
		return endpoint("/recommendations/anime");
	}

	@Override
	public TypeReference<DataListHolderWithPagination<Recommendation>> getResponseType() {
		return new TypeReference<DataListHolderWithPagination<Recommendation>>() { };
	}

	@Override
	public Flux<Recommendation> process(Mono<DataListHolderWithPagination<Recommendation>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
