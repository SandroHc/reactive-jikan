/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the manga user updates.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMangaUserUpdates">Jikan API docs - getMangaUserUpdates</a>
 */
public class MangaUserUpdatesQuery extends Query<DataListHolderWithPagination<UserUpdate>, Flux<UserUpdate>> {

	/** The manga ID. */
	private final int id;

	// TODO: validate javadoc by checking if max user updates per page has changed
	/** The page. Each page contains up to 100 user updates. */
	private final int page;

	public MangaUserUpdatesQuery(Jikan jikan, int id, int page) throws JikanInvalidArgumentException {
		super(jikan);
		if (page < 1) throw new JikanInvalidArgumentException("page starts at index 1");

		this.id = id;
		this.page = page;
	}

	@Override
	public String getUrl() {
		return endpoint("/manga/" + id + "/userupdates")
				.queryParam("page", page)
				.build();
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
