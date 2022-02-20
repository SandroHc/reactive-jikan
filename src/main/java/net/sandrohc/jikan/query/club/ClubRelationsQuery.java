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
 * Query for the club related entities.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getClubRelations">Jikan API docs - getClubRelations</a>
 */
public class ClubRelationsQuery extends Query<DataHolder<ClubRelations>, Mono<ClubRelations>> {

	/** The club ID. */
	protected final int id;

	public ClubRelationsQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/clubs/" + id + "/relations");
	}

	@Override
	public Mono<ClubRelations> process(Mono<DataHolder<ClubRelations>> content) {
		return content.map(holder -> holder.data);
	}
}
