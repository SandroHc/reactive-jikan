/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the anime recommendations.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeRecommendations">Jikan API docs - getAnimeRecommendations</a>
 */
public class AnimeRecommendationsQuery extends Query<DataListHolder<RecommendationSimple>, Flux<RecommendationSimple>> {

	/** The anime ID. */
	protected final int id;

	public AnimeRecommendationsQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/anime/" + id + "/recommendations");
	}

	@Override
	public Flux<RecommendationSimple> process(Mono<DataListHolder<RecommendationSimple>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
