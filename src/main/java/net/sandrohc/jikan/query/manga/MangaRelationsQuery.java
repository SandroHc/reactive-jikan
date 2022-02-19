/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the manga relations.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMangaRelations">Jikan API docs - getMangaRelations</a>
 */
public class MangaRelationsQuery extends Query<DataListHolder<Related>, Flux<Related>> {

	/** The manga ID. */
	private final int id;

	public MangaRelationsQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/manga/" + id + "/relations");
	}

	@Override
	public Flux<Related> process(Mono<DataListHolder<Related>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
