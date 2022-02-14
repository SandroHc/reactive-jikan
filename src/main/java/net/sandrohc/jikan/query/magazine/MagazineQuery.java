/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.magazine;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the latest magazines.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMagazines">Jikan API docs - getMagazines</a>
 */
public class MagazineQuery extends Query<DataListHolderWithPagination<EntityWithCount>, Flux<EntityWithCount>> {

	public MagazineQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public String getUrl() {
		return endpoint("/magazines").build();
	}

	@Override
	public TypeReference<DataListHolderWithPagination<EntityWithCount>> getResponseType() {
		return new TypeReference<DataListHolderWithPagination<EntityWithCount>>() { };
	}

	@Override
	public Flux<EntityWithCount> process(Mono<DataListHolderWithPagination<EntityWithCount>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
