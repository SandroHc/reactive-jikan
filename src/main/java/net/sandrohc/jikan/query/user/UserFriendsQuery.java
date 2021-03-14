/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.QueryFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserFriendsQuery extends QueryFlux<UserFriends, UserFriend> {

	/** The username. **/
	private final String username;

	/** The page. */
	private final int page;


	public UserFriendsQuery(Jikan jikan, String username, int page) {
		super(jikan);
		this.username = username;
		this.page = page;
	}

	@Override
	public String getUri() {
		return "/user/" + username + "/friends/" + page;
	}

	@Override
	public Class<UserFriends> getRequestClass() {
		return UserFriends.class;
	}

	@Override
	public Flux<UserFriend> process(Mono<UserFriends> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.friends));
	}

}
