/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the manga user updates.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMangaUserUpdates">Jikan API docs - getMangaUserUpdates</a>
 */
public class MangaUserUpdatesQuery extends Query<DataListHolderWithPagination<UserUpdateWithUser>, Flux<UserUpdateWithUser>> {

	/** The manga ID. */
	private final int id;

	public MangaUserUpdatesQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/manga/" + id + "/userupdates");
	}

	@Override
	public Flux<UserUpdateWithUser> process(Mono<DataListHolderWithPagination<UserUpdateWithUser>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
