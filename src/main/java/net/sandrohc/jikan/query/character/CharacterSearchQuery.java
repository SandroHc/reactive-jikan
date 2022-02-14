/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.character;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.*;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Query for the character search.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getCharactersSearch">Jikan API docs - getCharactersSearch</a>
 */
public class CharacterSearchQuery extends Query<DataListHolderWithPagination<Character>, Flux<Character>> {

	public static final int LIMIT_MAX = 50;

	protected Integer page;
	protected Integer limit;
	protected String query;
	protected CharacterOrderBy orderBy;
	protected SortOrder sort;
	protected String suffix;


	public CharacterSearchQuery(Jikan jikan) {
		super(jikan);
	}

	public CharacterSearchQuery page(Integer page) {
		this.page = page;
		return this;
	}

	public CharacterSearchQuery limit(Integer limit) throws JikanInvalidArgumentException {
		if (limit != null && (limit < 0 || limit > LIMIT_MAX))
			throw new JikanInvalidArgumentException("limit must be between 0 and " + LIMIT_MAX);

		this.limit = limit;
		return this;
	}

	public CharacterSearchQuery query(String query) throws UnsupportedEncodingException, JikanInvalidArgumentException {
		if (query == null)
			query = "";

		// TODO: encode on the QueryUrlBuilder
		// encode the string, as per the RFC3986 - http://tools.ietf.org/html/rfc3986#section-2.1 (percent encoding)
		String encoded = URLEncoder.encode(query, StandardCharsets.UTF_8.name()).replaceAll("\\+", "%20");

		this.query = encoded;
		return this;
	}

	public CharacterSearchQuery orderBy(CharacterOrderBy orderBy, SortOrder sortOrder) {
		this.orderBy = orderBy;
		this.sort = sortOrder;
		return this;
	}

	public CharacterSearchQuery suffix(String suffix) {
		this.suffix = suffix;
		return this;
	}

	@Override
	public String getUrl() {
		QueryUrlBuilder builder = QueryUrlBuilder.endpoint("/anime");
		if (page != null) builder.queryParam("page", page);
		if (limit != null) builder.queryParam("limit", limit);
		if (query != null) builder.queryParam("q", query);
		if (orderBy != null) builder.queryParam("order_by", orderBy.name().toLowerCase()); // TODO: add 'searchValue/queryParam' field to enum
		if (sort != null) builder.queryParam("sort", sort.search);
		if (suffix != null) builder.queryParam("letter", suffix);
		return builder.build();
	}

	@Override
	public TypeReference<DataListHolderWithPagination<Character>> getResponseType() {
		return new TypeReference<DataListHolderWithPagination<Character>>() { };
	}

	@Override
	public Flux<Character> process(Mono<DataListHolderWithPagination<Character>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
