/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.club;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrl.endpoint;

/**
 * Query for the club members.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getClubMembers">Jikan API docs - getClubMembers</a>
 */
public class ClubMembersQuery extends PageableQuery<DataListHolderWithPagination<User>, Flux<User>, ClubMembersQuery> {

	/** The club ID. */
	protected final int id;

	public ClubMembersQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrl getInnerUrl() {
		return endpoint("/clubs/" + id + "/members");
	}

	@Override
	public TypeReference<DataListHolderWithPagination<User>> getResponseType() {
		return new TypeReference<DataListHolderWithPagination<User>>() { };
	}

	@Override
	public Flux<User> process(Mono<DataListHolderWithPagination<User>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
