/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.user;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.legacy.user.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserFriendsQuery extends Query<UserFriend, Flux<UserFriend>> {

	/** The username. **/
	protected final String username;

	/** The page. */
	protected final int page;


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