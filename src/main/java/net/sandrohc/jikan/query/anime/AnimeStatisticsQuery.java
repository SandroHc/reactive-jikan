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
import net.sandrohc.jikan.query.QueryUrl;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrl.endpoint;

/**
 * Query for the anime statistics.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeStatistics">Jikan API docs - getAnimeStatistics</a>
 */
public class AnimeStatisticsQuery extends Query<DataHolder<Statistics>, Mono<Statistics>> {

	/** The anime ID. */
	protected final int id;

	public AnimeStatisticsQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrl getUrl() {
		return endpoint("/anime/" + id + "/statistics");
	}

	@Override
	public TypeReference<DataHolder<Statistics>> getResponseType() {
		return new TypeReference<DataHolder<Statistics>>() { };
	}

	@Override
	public Mono<Statistics> process(Mono<DataHolder<Statistics>> content) {
		return content.map(results -> results.data);
	}
}