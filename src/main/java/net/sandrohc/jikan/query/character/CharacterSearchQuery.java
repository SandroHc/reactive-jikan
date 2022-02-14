/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.character;

import java.lang.Character;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.character.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.QueryUrl;
import net.sandrohc.jikan.query.QueryableQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Query for the character search.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getCharactersSearch">Jikan API docs - getCharactersSearch</a>
 */
public class CharacterSearchQuery extends QueryableQuery<DataListHolderWithPagination<Character>, Flux<Character>, CharacterSearchQuery> {

	protected CharacterOrderBy orderBy;
	protected SortOrder sort;
	protected String suffix;


	public CharacterSearchQuery(Jikan jikan) {
		super(jikan);
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
	public QueryUrl getInnerUrl() {
		return QueryUrl.endpoint("/anime")
				.param("order_by", orderBy, CharacterOrderBy::getSearch)
				.param("sort", sort, SortOrder::getSearch)
				.param("letter", suffix);
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