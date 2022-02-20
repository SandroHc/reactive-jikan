/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the user profile.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getUserProfile">Jikan API docs - getUserProfile</a>
 */
public class UserProfileQuery extends Query<DataHolder<User>, Mono<User>> {

	/** The user name. */
	protected final String username;

	public UserProfileQuery(Jikan jikan, String username) {
		super(jikan);
		this.username = username;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/users/" + username);
	}

	@Override
	public Mono<User> process(Mono<DataHolder<User>> content) {
		return content.map(holder -> holder.data);
	}
}
