/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the manga recommendations.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMangaRecommendations">Jikan API docs - getMangaRecommendations</a>
 */
public class MangaRecommendationsQuery extends Query<DataListHolder<RecommendationSimple>, Flux<RecommendationSimple>> {

	/** The manga ID. */
	private final int id;

	public MangaRecommendationsQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/manga/" + id + "/recommendations");
	}

	@Override
	public Flux<RecommendationSimple> process(Mono<DataListHolder<RecommendationSimple>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
