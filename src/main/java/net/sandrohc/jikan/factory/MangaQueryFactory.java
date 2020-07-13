/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.query.manga.MangaQuery;
import net.sandrohc.jikan.query.search.MangaSearchQuery;

public class MangaQueryFactory extends Factory {

    public MangaQueryFactory(Jikan jikan) {
        super(jikan);
    }

    public MangaQuery get(int id) {
        return new MangaQuery(this.jikan, id);
    }

    public MangaSearchQuery search() {
        return new MangaSearchQuery(this.jikan);
    }

}
