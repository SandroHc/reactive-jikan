/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AnimeUserUpdatesQuery extends Query<UserUpdate, Flux<UserUpdate>> {

	/** The manga ID. */
	private final int id;

	/** The page. Each page contains up to 100 user updates. */
	private final int page;

	public AnimeUserUpdatesQuery(Jikan jikan, int id, int page) throws JikanInvalidArgumentException {
		super(jikan);
		if (page < 1) throw new JikanInvalidArgumentException("page starts at index 1");

		this.id = id;
		this.page = page;
	}

	@Override
	public String getUri() {
		return "/anime/" + id + "/userupdates/" + page;
	}

	@Override
	public Class<UserUpdate> getRequestClass() {
		return UserUpdate.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return UserUpdates.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<UserUpdate> process(Mono<?> content) {
		return ((Mono<UserUpdates>) content).flatMapMany(results -> Flux.fromIterable(results.users));
	}

}
