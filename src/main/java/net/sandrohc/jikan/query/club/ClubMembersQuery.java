/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.club;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the club members.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getClubMembers">Jikan API docs - getClubMembers</a>
 */
public class ClubMembersQuery extends PageableQuery<DataListHolderWithPagination<UserSimple>, Flux<UserSimple>, ClubMembersQuery> {

	/** The club ID. */
	protected final int id;

	public ClubMembersQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create().path("/clubs/" + id + "/members");
	}

	@Override
	public Flux<UserSimple> process(Mono<DataListHolderWithPagination<UserSimple>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
