/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserFriendsQuery extends Query<UserFriend, Flux<UserFriend>> {

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
	public Class<UserFriend> getRequestClass() {
		return UserFriend.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return UserFriends.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<UserFriend> process(Mono<?> content) {
		return ((Mono<UserFriends>) content).flatMapMany(results -> Flux.fromIterable(results.friends));
	}

}
