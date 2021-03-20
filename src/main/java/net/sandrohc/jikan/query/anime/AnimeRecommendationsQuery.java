/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AnimeRecommendationsQuery extends Query<Recommendation, Flux<Recommendation>> {

	/** The anime ID. */
	private final int id;

	public AnimeRecommendationsQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUri() {
		return "/anime/" + id + "/recommendations";
	}

	@Override
	public Class<Recommendation> getRequestClass() {
		return Recommendation.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return Recommendations.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<Recommendation> process(Mono<?> content) {
		return ((Mono<Recommendations>) content).flatMapMany(results -> Flux.fromIterable(results.recommendations));
	}
}
