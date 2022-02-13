/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.club;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.legacy.club.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ClubMembersQuery extends Query<ClubMember, Flux<ClubMember>> {

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
	public Class<ClubMember> getRequestClass() {
		return ClubMember.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return ClubMembers.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<ClubMember> process(Mono<?> content) {
		return ((Mono<ClubMembers>) content).flatMapMany(results -> Flux.fromIterable(results.members));
	}

}
