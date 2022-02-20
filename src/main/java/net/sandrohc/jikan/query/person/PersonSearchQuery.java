/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.person;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.person.*;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import net.sandrohc.jikan.query.QueryableQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the person search.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getPeopleSearch">Jikan API docs - getPeopleSearch</a>
 */
public class PersonSearchQuery extends QueryableQuery<DataListHolderWithPagination<Person>, Flux<Person>, PersonSearchQuery> {

	protected PersonOrderBy orderBy;
	protected SortOrder sort;
	protected String suffix;

	public PersonSearchQuery(Jikan jikan) {
		super(jikan);
	}


	public PersonSearchQuery orderBy(PersonOrderBy orderBy, SortOrder sortOrder) {
		this.orderBy = orderBy;
		this.sort = sortOrder;
		return this;
	}

	public PersonSearchQuery suffix(String suffix) {
		this.suffix = suffix;
		return this;
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create()
				.path("/people")
				.param("order_by", orderBy, PersonOrderBy::getSearch)
				.param("sort", sort, SortOrder::getSearch)
				.param("letter", suffix);
	}

	@Override
	public Flux<Person> process(Mono<DataListHolderWithPagination<Person>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
