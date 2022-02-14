/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.genre;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.genre.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Base query for the genres search.
 */
public abstract class GenreQuery extends Query<DataListHolder<EntityWithCount>, Flux<EntityWithCount>> {

	public static final int LIMIT_MAX = 50;

	protected Integer page;
	protected Integer limit;
	protected GenreType type;

	public GenreQuery(Jikan jikan) {
		super(jikan);
	}

	public GenreQuery page(Integer page) throws JikanInvalidArgumentException {
		if (page != null && page < 1)
			throw new JikanInvalidArgumentException("page must be a positive integer");
		this.page = page;
		return this;
	}

	public GenreQuery limit(Integer limit) throws JikanInvalidArgumentException {
		if (limit != null && (limit < 0 || limit > LIMIT_MAX))
			throw new JikanInvalidArgumentException("limit must be between 0 and " + LIMIT_MAX);

		this.limit = limit;
		return this;
	}

	public GenreQuery type(GenreType type) {
		this.type = type;
		return this;
	}

	protected void addQueryParams(QueryUrlBuilder builder) {
		if (page != null) builder.queryParam("page", page);
		if (limit != null) builder.queryParam("limit", limit);
		if (type != null) builder.queryParam("filter", type.search);
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
