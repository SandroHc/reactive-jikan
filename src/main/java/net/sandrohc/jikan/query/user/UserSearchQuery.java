/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.QueryUrl;
import net.sandrohc.jikan.query.QueryableQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Query for the user search.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getUsersSearch">Jikan API docs - getUsersSearch</a>
 */
public class UserSearchQuery extends QueryableQuery<DataListHolderWithPagination<UserSimple>, Flux<UserSimple>, UserSearchQuery> {

	protected UserGender gender;
	protected String location;
	protected Integer maxAge;
	protected Integer minAge;


	public UserSearchQuery(Jikan jikan) {
		super(jikan);
	}

	public UserSearchQuery gender(UserGender gender) {
		this.gender = gender;
		return this;
	}

	public UserSearchQuery location(String location) {
		this.location = location;
		return this;
	}

	public UserSearchQuery maxAge(Integer maxAge) {
		this.maxAge = maxAge;
		return this;
	}

	public UserSearchQuery minAge(Integer minAge) {
		this.minAge = minAge;
		return this;
	}

	@Override
	public QueryUrl getInnerUrl() {
		return QueryUrl.endpoint("/anime")
				.param("gender", gender, UserGender::getSearch)
				.param("location", location)
				.param("maxAge", maxAge)
				.param("minAge", minAge);
	}

	@Override
	public TypeReference<DataListHolderWithPagination<UserSimple>> getResponseType() {
		return new TypeReference<DataListHolderWithPagination<UserSimple>>() { };
	}

	@Override
	public Flux<UserSimple> process(Mono<DataListHolderWithPagination<UserSimple>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
