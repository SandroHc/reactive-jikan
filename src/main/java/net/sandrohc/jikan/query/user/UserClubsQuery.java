/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrl.endpoint;

/**
 * Get the list of clubs the user joined.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getUserClubs">Jikan API docs - getUserClubs</a>
 */
public class UserClubsQuery extends PageableQuery<DataListHolderWithPagination<Entity>, Flux<Entity>, UserClubsQuery> {

	/** The user name. */
	protected final String username;

	public UserClubsQuery(Jikan jikan, String username) {
		super(jikan);
		this.username = username;
	}

	@Override
	public QueryUrl getInnerUrl() {
		return endpoint("/users/" + username + "/clubs");
	}

	@Override
	public TypeReference<DataListHolderWithPagination<Entity>> getResponseType() {
		return new TypeReference<DataListHolderWithPagination<Entity>>() { };
	}

	@Override
	public Flux<Entity> process(Mono<DataListHolderWithPagination<Entity>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
