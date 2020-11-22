/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.club;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.club.*;
import net.sandrohc.jikan.query.QueryFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ClubMembersQuery extends QueryFlux<ClubMembers, ClubMember> {

	/**
	 * The club ID.
	 */
	private final int id;

	/**
	 * The members page. Each page contains up to 35 members.
	 */
	private final int page;


	public ClubMembersQuery(Jikan jikan, int id, int page) {
		super(jikan);
		this.id = id;
		this.page = page;
	}

	@Override
	public String getUri() {
		return "/club/" + id + "/members/" + page;
	}

	@Override
	public Class<ClubMembers> getRequestClass() {
		return ClubMembers.class;
	}

	@Override
	public Flux<ClubMember> process(Mono<ClubMembers> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.members));
	}

}
