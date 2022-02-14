/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.club;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.*;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.club.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Query for the club search.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getClubsSearch">Jikan API docs - getClubsSearch</a>
 */
public class ClubSearchQuery extends Query<DataListHolderWithPagination<Club>, Flux<Club>> {

	public static final int LIMIT_MAX = 50;

	protected Integer page;
	protected Integer limit;
	protected String query;
	protected ClubType type;
	protected ClubCategory category;
	protected ClubOrderBy orderBy;
	protected SortOrder sort;
	protected String suffix;


	public ClubSearchQuery(Jikan jikan) {
		super(jikan);
	}

	public ClubSearchQuery page(Integer page) {
		this.page = page;
		return this;
	}

	public ClubSearchQuery limit(Integer limit) throws JikanInvalidArgumentException {
		if (limit != null && (limit < 0 || limit > LIMIT_MAX))
			throw new JikanInvalidArgumentException("limit must be between 0 and " + LIMIT_MAX);

		this.limit = limit;
		return this;
	}

	public ClubSearchQuery query(String query) throws UnsupportedEncodingException, JikanInvalidArgumentException {
		if (query == null)
			query = "";

		// TODO: encode on the QueryUrlBuilder
		// encode the string, as per the RFC3986 - http://tools.ietf.org/html/rfc3986#section-2.1 (percent encoding)
		String encoded = URLEncoder.encode(query, StandardCharsets.UTF_8.name()).replaceAll("\\+", "%20");

		this.query = encoded;
		return this;
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
	public String getUrl() {
		QueryUrlBuilder builder = QueryUrlBuilder.endpoint("/clubs");
		if (page != null) builder.queryParam("page", page);
		if (limit != null) builder.queryParam("limit", limit);
		if (query != null) builder.queryParam("q", query);
		if (type != null) builder.queryParam("type", type.search);
		if (category != null) builder.queryParam("category", category.search);
		if (orderBy != null) builder.queryParam("order_by", orderBy.search);
		if (sort != null) builder.queryParam("sort", sort.search);
		if (suffix != null) builder.queryParam("letter", suffix);
		return builder.build();
	}

	@Override
	public TypeReference<DataListHolderWithPagination<Club>> getResponseType() {
		return new TypeReference<DataListHolderWithPagination<Club>>() { };
	}

	@Override
	public Flux<Club> process(Mono<DataListHolderWithPagination<Club>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
