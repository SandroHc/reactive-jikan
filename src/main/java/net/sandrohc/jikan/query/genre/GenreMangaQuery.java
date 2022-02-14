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
 * Query for the manga genre search.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMangaGenres">Jikan API docs - getMangaGenres</a>
 */
public class GenreMangaQuery extends GenreQuery<GenreMangaQuery> {

	public GenreMangaQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	protected QueryUrl getGenreQueryUrl() {
		return endpoint("/genres/manga");
	}
}
