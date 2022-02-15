/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.query.genre.GenreAnimeQuery;
import net.sandrohc.jikan.query.genre.GenreMangaQuery;

/**
 * Factory for the genre queries.
 *
 * @see <a href="https://docs.api.jikan.moe/#tag/genres">Jikan API docs - genres</a>
 */
public class GenreQueryFactory extends Factory {

    public GenreQueryFactory(Jikan jikan) {
        super(jikan);
    }

    /**
     * Get the anime genre search.
     *
     * @return The anime genre query
     * @see <a href="https://docs.api.jikan.moe/#operation/getClubsById">Jikan API docs - getClubsById</a>
     */
    public GenreAnimeQuery anime() {
        return new GenreAnimeQuery(this.jikan);
    }

    /**
     * Get the manga genre search.
     *
     * @return The manga genre query
     * @see <a href="https://docs.api.jikan.moe/#operation/getClubsById">Jikan API docs - getClubsById</a>
     */
    public GenreMangaQuery manga() {
        return new GenreMangaQuery(this.jikan);
    }
}
