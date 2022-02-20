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
 * Query for the user recent updates.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getUserUpdates">Jikan API docs - getUserUpdates</a>
 */
public class UserUpdatesQuery extends Query<DataHolder<UserUpdates>, Mono<UserUpdates>> {

	/** The user name. */
	protected final String username;

	public UserUpdatesQuery(Jikan jikan, String username) {
		super(jikan);
		this.username = username;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/users/" + username + "/userupdates");
	}

	@Override
	public Mono<UserUpdates> process(Mono<DataHolder<UserUpdates>> content) {
		return content.map(holder -> holder.data);
	}
}
