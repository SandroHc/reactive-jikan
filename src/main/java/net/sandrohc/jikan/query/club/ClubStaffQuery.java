/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.club;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the club staff.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getClubStaff">Jikan API docs - getClubStaff</a>
 */
public class ClubStaffQuery extends Query<DataListHolder<UserSimple>, Flux<UserSimple>> {

	/** The club ID. */
	protected final int id;

	public ClubStaffQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return endpoint("/clubs/" + id + "/staff");
	}

	@Override
	public TypeReference<DataListHolder<UserSimple>> getResponseType() {
		return new TypeReference<DataListHolder<UserSimple>>() { };
	}

	@Override
	public Flux<UserSimple> process(Mono<DataListHolder<UserSimple>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
