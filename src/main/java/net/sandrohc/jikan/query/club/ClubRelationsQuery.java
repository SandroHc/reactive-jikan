/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.club;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the club related entities.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getClubRelations">Jikan API docs - getClubRelations</a>
 */
public class ClubRelationsQuery extends Query<DataListHolder<EntityWithType>, Flux<EntityWithType>> {

	/** The club ID. */
	protected final int id;

	public ClubRelationsQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return endpoint("/clubs/" + id + "/relations");
	}

	@Override
	public TypeReference<DataListHolder<EntityWithType>> getResponseType() {
		return new TypeReference<DataListHolder<EntityWithType>>() { };
	}

	@Override
	public Flux<EntityWithType> process(Mono<DataListHolder<EntityWithType>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
