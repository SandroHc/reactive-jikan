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
 * Query for the manga user updates.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMangaUserUpdates">Jikan API docs - getMangaUserUpdates</a>
 */
public class MangaUserUpdatesQuery extends Query<DataListHolderWithPagination<UserUpdate>, Flux<UserUpdate>> {

	/** The manga ID. */
	private final int id;

	public MangaUserUpdatesQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrl getUrl() {
		return endpoint("/manga/" + id + "/userupdates");
	}

	@Override
	public TypeReference<DataListHolderWithPagination<UserUpdate>> getResponseType() {
		return new TypeReference<DataListHolderWithPagination<UserUpdate>>() { };
	}

	@Override
	public Flux<UserUpdate> process(Mono<DataListHolderWithPagination<UserUpdate>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
