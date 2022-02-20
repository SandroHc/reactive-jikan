/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.club;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.club.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import net.sandrohc.jikan.query.QueryableQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the club search.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getClubsSearch">Jikan API docs - getClubsSearch</a>
 */
public class ClubSearchQuery extends QueryableQuery<DataListHolderWithPagination<Club>, Flux<Club>, ClubSearchQuery> {

	protected ClubType type;
	protected ClubCategory category;
	protected ClubOrderBy orderBy;
	protected SortOrder sort;
	protected String suffix;


	public ClubSearchQuery(Jikan jikan) {
		super(jikan);
	}

	public ClubSearchQuery type(ClubType type) {
		this.type = type;
		return this;
	}

	public ClubSearchQuery category(ClubCategory category) {
		this.category = category;
		return this;
	}

	public ClubSearchQuery orderBy(ClubOrderBy orderBy, SortOrder sortOrder) {
		this.orderBy = orderBy;
		this.sort = sortOrder;
		return this;
	}

	public ClubSearchQuery suffix(String suffix) {
		this.suffix = suffix;
		return this;
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create()
				.path("/clubs")
				.param("type", type.search)
				.param("category", category, ClubCategory::getSearch)
				.param("order_by", orderBy, ClubOrderBy::getSearch)
				.param("sort", sort, SortOrder::getSearch)
				.param("letter", suffix);
	}

	@Override
	public Flux<Club> process(Mono<DataListHolderWithPagination<Club>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
