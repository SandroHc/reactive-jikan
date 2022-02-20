/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.random;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.person.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for a random person.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getRandomPeople">Jikan API docs - getRandomPeople</a>
 */
public class RandomPersonQuery extends Query<DataHolder<Person>, Mono<Person>> {

	public RandomPersonQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/random/people");
	}

	@Override
	public Mono<Person> process(Mono<DataHolder<Person>> content) {
		return content.map(holder -> holder.data);
	}
}
