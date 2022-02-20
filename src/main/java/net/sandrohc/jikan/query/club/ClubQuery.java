/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.club;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.club.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the club details.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getClubsById">Jikan API docs - getClubsById</a>
 */
public class ClubQuery extends Query<DataHolder<Club>, Mono<Club>> {

	/** The club ID. */
	protected final int id;

	public ClubQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/clubs/" + id);
	}

	@Override
	public Mono<Club> process(Mono<DataHolder<Club>> content) {
		return content.map(holder -> holder.data);
	}
}
