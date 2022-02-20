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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the user recent anime/manga history.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getUserHistory">Jikan API docs - getUserHistory</a>
 */
public class UserHistoryQuery extends Query<DataListHolder<UserHistoryEntry>, Flux<UserHistoryEntry>> {

	/** The history type. */
	protected final String type;

	/** The user name. **/
	protected final String username;


	public UserHistoryQuery(Jikan jikan, String type, String username) {
		super(jikan);
		this.type = type;
		this.username = username;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create()
				.path("/users/" + username + "/history")
				.param("type", type);
	}

	@Override
	public Flux<UserHistoryEntry> process(Mono<DataListHolder<UserHistoryEntry>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
