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
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the club members.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getClubMembers">Jikan API docs - getClubMembers</a>
 */
public class ClubMembersQuery extends Query<DataListHolderWithPagination<User>, Flux<User>> {

	/** The club ID. */
	private final int id;

	// TODO: confirm max per page
	/** The page number. Each page contains up to 35 members. */
	private final int page;

	public ClubMembersQuery(Jikan jikan, int id, int page) {
		super(jikan);
		this.id = id;
		this.page = page;
	}

	@Override
	public String getUrl() {
		return endpoint("/clubs/" + id + "/members")
				.queryParam("page", page)
				.build();
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
