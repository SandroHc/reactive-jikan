/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrl.endpoint;

/**
 * Query for the anime user updates.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeUserUpdates">Jikan API docs - getAnimeUserUpdates</a>
 */
public class AnimeUserUpdatesQuery extends Query<DataListHolderWithPagination<UserUpdateWithUser>, Flux<UserUpdateWithUser>> {

	/** The anime ID. */
	protected final int id;

	// TODO: validate javadoc by checking if max user updates per page has changed
	/** The page. Each page contains up to 100 user updates. */
	protected final int page;

	public AnimeUserUpdatesQuery(Jikan jikan, int id, int page) throws JikanInvalidArgumentException {
		super(jikan);
		if (page < 1) throw new JikanInvalidArgumentException("page starts at index 1");

		this.id = id;
		this.page = page;
	}

	@Override
	public QueryUrl getUrl() {
		return endpoint("/anime/" + id + "/userupdates")
				.param("page", page);
	}

	@Override
	public TypeReference<DataListHolderWithPagination<UserUpdateWithUser>> getResponseType() {
		return new TypeReference<DataListHolderWithPagination<UserUpdateWithUser>>() { };
	}

	@Override
	public Flux<UserUpdateWithUser> process(Mono<DataListHolderWithPagination<UserUpdateWithUser>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
