/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.producer;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import net.sandrohc.jikan.query.QueryableQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the producers.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getProducers">Jikan API docs - getProducers</a>
 */
public class ProducerQuery extends QueryableQuery<DataListHolderWithPagination<EntityWithCount>, Flux<EntityWithCount>, ProducerQuery> {

	public ProducerQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return endpoint("/producer");
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
