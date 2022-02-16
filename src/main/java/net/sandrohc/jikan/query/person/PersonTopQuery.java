/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.person;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.person.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrl.endpoint;

/**
 * Get the most popular person by number of favorites.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getTopPeople">Jikan API docs - getTopPeople</a>
 */
public class PersonTopQuery extends PageableQuery<DataListHolderWithPagination<Person>, Flux<Person>, PersonTopQuery> {

	public PersonTopQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public QueryUrl getInnerUrl() {
		return endpoint("/top/people");
	}

	@Override
	public TypeReference<DataListHolderWithPagination<Person>> getResponseType() {
		return new TypeReference<DataListHolderWithPagination<Person>>() { };
	}

	@Override
	public Flux<Person> process(Mono<DataListHolderWithPagination<Person>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
