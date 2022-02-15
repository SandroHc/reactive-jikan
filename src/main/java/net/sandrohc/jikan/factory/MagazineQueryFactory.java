/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.query.magazine.MagazineQuery;

/**
 * Factory for the magazine queries.
 *
 * @see <a href="https://docs.api.jikan.moe/#tag/magazines">Jikan API docs - magazines</a>
 */
public class MagazineQueryFactory extends Factory {

    public MagazineQueryFactory(Jikan jikan) {
        super(jikan);
    }

    /**
     * Get the latest magazines.
     *
     * @return The magazine query
     * @see <a href="https://docs.api.jikan.moe/#operation/getMagazines">Jikan API docs - getMagazines</a>
     */
    public MagazineQuery list() {
        return new MagazineQuery(this.jikan);
    }
}
