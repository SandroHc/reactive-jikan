/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.random;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for a random user.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getRandomUsers">Jikan API docs - getRandomUsers</a>
 */
public class RandomUserQuery extends Query<DataHolder<User>, Mono<User>> {

	public RandomUserQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/random/users");
	}

	@Override
	public Mono<User> process(Mono<DataHolder<User>> content) {
		return content.map(holder -> holder.data);
	}
}
