/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrl.endpoint;

/**
 * Query for the manga recommendations.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMangaRecommendations">Jikan API docs - getMangaRecommendations</a>
 */
public class MangaRecommendationsQuery extends Query<DataListHolder<Recommendation>, Flux<Recommendation>> {

	/** The manga ID. */
	private final int id;

	public MangaRecommendationsQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrl getUrl() {
		return endpoint("/manga/" + id + "/recommendations");
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
