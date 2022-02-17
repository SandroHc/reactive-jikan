/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the anime recommendations.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeRecommendations">Jikan API docs - getAnimeRecommendations</a>
 */
public class AnimeRecommendationsQuery extends Query<DataListHolder<Recommendation>, Flux<Recommendation>> {

	/** The anime ID. */
	protected final int id;

	public AnimeRecommendationsQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return endpoint("/anime/" + id + "/recommendations");
	}

	@Override
	public TypeReference<DataListHolder<Recommendation>> getResponseType() {
		return new TypeReference<DataListHolder<Recommendation>>() { };
	}

	@Override
	public Flux<Recommendation> process(Mono<DataListHolder<Recommendation>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
