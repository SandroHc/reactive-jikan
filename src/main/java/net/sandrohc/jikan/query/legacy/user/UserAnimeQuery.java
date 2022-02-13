/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.user;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.legacy.enums.*;
import net.sandrohc.jikan.model.legacy.user.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserAnimeQuery extends Query<UserAnime, Flux<UserAnime>> {

	/** The username. **/
	private final String username;

	/** The page. */
	private final int page;

	/** The watching status. */
	private UserAnimeWatchingStatus status;


	public UserAnimeQuery(Jikan jikan, String username, int page) {
		super(jikan);
		this.username = username;
		this.page = page;
		this.status = UserAnimeWatchingStatus.ALL;
	}

	public UserAnimeQuery status(UserAnimeWatchingStatus status) throws JikanInvalidArgumentException {
		if (status == null)
			throw new JikanInvalidArgumentException("status must not be null");
		this.status = status;
		return this;
	}

	@Override
	public String getUri() {
		return "/user/" + username + "/animelist/" + status.status + '/' + page;
	}

	@Override
	public Class<UserAnime> getRequestClass() {
		return UserAnime.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return UserAnimeList.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<UserAnime> process(Mono<?> content) {
		return ((Mono<UserAnimeList>) content).flatMapMany(results -> Flux.fromIterable(results.anime));
	}

}
