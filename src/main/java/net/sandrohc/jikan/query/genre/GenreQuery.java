/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.genre;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.genre.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Base query for the genres search.
 */
public abstract class GenreQuery<Q extends GenreQuery<?>> extends PageableQuery<DataListHolder<EntityWithCount>, Flux<EntityWithCount>, Q> {

	protected GenreType type;


	public GenreQuery(Jikan jikan) {
		super(jikan);
	}

	public GenreQuery<Q> type(GenreType type) {
		this.type = type;
		return this;
	}

	protected abstract QueryUrlBuilder getGenreQueryUrl();

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return getGenreQueryUrl()
				.param("filter", type, GenreType::getSearch);
	}

	@Override
	public TypeReference<DataListHolder<EntityWithCount>> getResponseType() {
		return new TypeReference<DataListHolder<EntityWithCount>>() { };
	}

	@Override
	public Flux<EntityWithCount> process(Mono<DataListHolder<EntityWithCount>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
