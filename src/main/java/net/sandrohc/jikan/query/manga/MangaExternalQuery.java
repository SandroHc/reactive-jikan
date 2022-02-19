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
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the manga external links.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMangaExternal">Jikan API docs - getMangaExternal</a>
 */
public class MangaExternalQuery extends Query<DataListHolder<External>, Flux<External>> {

	/** The manga ID. */
	protected final int id;

	public MangaExternalQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/manga/" + id + "/external");
	}

	@Override
	public Flux<External> process(Mono<DataListHolder<External>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
