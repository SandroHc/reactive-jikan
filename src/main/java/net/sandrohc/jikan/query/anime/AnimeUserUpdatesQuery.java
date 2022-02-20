/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the anime user updates.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeUserUpdates">Jikan API docs - getAnimeUserUpdates</a>
 */
public class AnimeUserUpdatesQuery extends PageableQuery<DataListHolderWithPagination<UserUpdateWithUser>, Flux<UserUpdateWithUser>, AnimeUserUpdatesQuery> {

	/** The anime ID. */
	protected int id;

	public AnimeUserUpdatesQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create()
				.path("/anime/" + id + "/userupdates")
				.param("page", page);
	}

	@Override
	public Flux<UserUpdateWithUser> process(Mono<DataListHolderWithPagination<UserUpdateWithUser>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
