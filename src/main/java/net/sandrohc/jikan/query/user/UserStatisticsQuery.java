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
 * Query for the user statistics.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getUserStatistics">Jikan API docs - getUserStatistics</a>
 */
public class UserStatisticsQuery extends Query<DataHolder<UserStatistics>, Mono<UserStatistics>> {

	/** The user name. */
	protected final String username;

	public UserStatisticsQuery(Jikan jikan, String username) {
		super(jikan);
		this.username = username;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/users/" + username + "/statistics");
	}

	@Override
	public Mono<UserStatistics> process(Mono<DataHolder<UserStatistics>> content) {
		return content.map(holder -> holder.data);
	}
}
