/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Get the list of reviews written by the user.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getUserReviews">Jikan API docs - getUserReviews</a>
 */
public class UserReviewsQuery extends PageableQuery<DataListHolderWithPagination<Review>, Flux<Review>, UserReviewsQuery> {

	/** The user name. */
	protected final String username;

	public UserReviewsQuery(Jikan jikan, String username) {
		super(jikan);
		this.username = username;
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create().path("/users/" + username + "/reviews");
	}

	@Override
	public Flux<Review> process(Mono<DataListHolderWithPagination<Review>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
