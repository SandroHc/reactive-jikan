/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrl.endpoint;

/**
 * Get the list of recommendations by the user.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getUserRecommendations">Jikan API docs - getUserRecommendations</a>
 */
public class UserRecommendationsQuery extends PageableQuery<DataListHolderWithPagination<Recommendation>, Flux<Recommendation>, UserRecommendationsQuery> {

	/** The user name. */
	protected final String username;

	public UserRecommendationsQuery(Jikan jikan, String username) {
		super(jikan);
		this.username = username;
	}

	@Override
	public QueryUrl getInnerUrl() {
		return endpoint("/users/" + username + "/recommendations");
	}

	@Override
	public TypeReference<DataListHolderWithPagination<Recommendation>> getResponseType() {
		return new TypeReference<DataListHolderWithPagination<Recommendation>>() { };
	}

	@Override
	public Flux<Recommendation> process(Mono<DataListHolderWithPagination<Recommendation>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
