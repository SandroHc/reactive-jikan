/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.genre;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.query.QueryUrl;

import static net.sandrohc.jikan.query.QueryUrl.endpoint;

/**
 * Query for the anime genre search.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeGenres">Jikan API docs - getAnimeGenres</a>
 */
public class GenreAnimeQuery extends GenreQuery<GenreAnimeQuery> {

	public GenreAnimeQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	protected QueryUrl getGenreQueryUrl() {
		return endpoint("/genres/anime");
	}
}
