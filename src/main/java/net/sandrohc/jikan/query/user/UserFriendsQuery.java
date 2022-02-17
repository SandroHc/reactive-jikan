/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Get the user list of friends, if public.
 * <p>
 * Each request is limited to 100 results per page.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getUserFriends">Jikan API docs - getUserFriends</a>
 */
public class UserFriendsQuery extends PageableQuery<DataListHolderWithPagination<UserFriend>, Flux<UserFriend>, UserFriendsQuery> {

	/** The username. **/
	protected final String username;

	public UserFriendsQuery(Jikan jikan, String username) {
		super(jikan);
		this.username = username;
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return QueryUrlBuilder.endpoint("/users/" + username + "/friends");
	}

	@Override
	public TypeReference<DataListHolderWithPagination<UserFriend>> getResponseType() {
		return new TypeReference<DataListHolderWithPagination<UserFriend>>() { };
	}

	@Override
	public Flux<UserFriend> process(Mono<DataListHolderWithPagination<UserFriend>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
