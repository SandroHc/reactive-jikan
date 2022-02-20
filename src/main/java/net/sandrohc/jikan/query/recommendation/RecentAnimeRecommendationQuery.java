/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.recommendation;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for a list of the latest anime recommendations.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getRecentAnimeRecommendations">Jikan API docs - getRecentAnimeRecommendations</a>
 */
public class RecentAnimeRecommendationQuery extends PageableQuery<DataListHolderWithPagination<Recommendation>, Flux<Recommendation>, RecentAnimeRecommendationQuery> {

	public RecentAnimeRecommendationQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create().path("/recommendations/anime");
	}

	@Override
	public Flux<Recommendation> process(Mono<DataListHolderWithPagination<Recommendation>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
