/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the user favorites.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getUserFavorites">Jikan API docs - getUserFavorites</a>
 */
public class UserFavoritesQuery extends Query<DataHolder<UserFavorites>, Mono<UserFavorites>> {

	/** The user name. */
	protected final String username;

	public UserFavoritesQuery(Jikan jikan, String username) {
		super(jikan);
		this.username = username;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/users/" + username + "/favorites");
	}

	@Override
	public Mono<UserFavorites> process(Mono<DataHolder<UserFavorites>> content) {
		return content.map(holder -> holder.data);
	}
}
