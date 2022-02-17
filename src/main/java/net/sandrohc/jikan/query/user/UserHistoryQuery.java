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
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Query for the user recent history.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getUserHistory">Jikan API docs - getUserHistory</a>
 */
public abstract class UserHistoryQuery extends Query<DataListHolder<UserHistoryEntry>, Flux<UserHistoryEntry>> {

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
		return QueryUrlBuilder.endpoint("/users/" + username + "/history")
				.param("type", type);
	}

	@Override
	public TypeReference<DataListHolder<UserHistoryEntry>> getResponseType() {
		return new TypeReference<DataListHolder<UserHistoryEntry>>() { };
	}

	@Override
	public Flux<UserHistoryEntry> process(Mono<DataListHolder<UserHistoryEntry>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
