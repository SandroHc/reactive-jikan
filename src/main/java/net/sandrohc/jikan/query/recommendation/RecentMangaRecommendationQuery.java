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
 * Query for a list of the latest manga recommendations.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getRecentMangaRecommendations">Jikan API docs - getRecentMangaRecommendations</a>
 */
public class RecentMangaRecommendationQuery extends PageableQuery<DataListHolderWithPagination<Recommendation>, Flux<Recommendation>, RecentMangaRecommendationQuery> {

	public RecentMangaRecommendationQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create().path("/recommendations/manga");
	}

	@Override
	public Flux<Recommendation> process(Mono<DataListHolderWithPagination<Recommendation>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
