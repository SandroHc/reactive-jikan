/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.QueryFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MangaUserUpdatesQuery extends QueryFlux<UserUpdates, UserUpdate> {

	/** The manga ID. */
	private final int id;

	/** The page. Each page contains up to 100 user updates. */
	private final int page;

	public MangaUserUpdatesQuery(Jikan jikan, int id, int page) throws JikanInvalidArgumentException {
		super(jikan);
		if (page < 1) throw new JikanInvalidArgumentException("page starts at index 1");

		this.id = id;
		this.page = page;
	}

	@Override
	public String getUri() {
		return "/manga/" + id + "/userupdates/" + page;
	}

	@Override
	public Class<UserUpdates> getRequestClass() {
		return UserUpdates.class;
	}

	@Override
	public Flux<UserUpdate> process(Mono<UserUpdates> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.users));
	}

}
