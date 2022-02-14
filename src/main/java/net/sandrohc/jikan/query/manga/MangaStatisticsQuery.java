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
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the manga statistics.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMangaStatistics">Jikan API docs - getMangaStatistics</a>
 */
public class MangaStatisticsQuery extends Query<DataHolder<Statistics>, Mono<Statistics>> {

	/** The manga ID. */
	private final int id;

	public MangaStatisticsQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUrl() {
		return endpoint("/manga/" + id + "/statistics").build();
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
