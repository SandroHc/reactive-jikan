/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.club.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Get the list of clubs the user joined.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getUserClubs">Jikan API docs - getUserClubs</a>
 */
public class UserClubsQuery extends PageableQuery<DataListHolderWithPagination<ClubSimple>, Flux<ClubSimple>, UserClubsQuery> {

	/** The user name. */
	protected final String username;

	public UserClubsQuery(Jikan jikan, String username) {
		super(jikan);
		this.username = username;
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create().path("/users/" + username + "/clubs");
	}

	@Override
	public Flux<ClubSimple> process(Mono<DataListHolderWithPagination<ClubSimple>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
