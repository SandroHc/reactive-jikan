/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;

public class QueryFactory extends Factory {

    public QueryFactory(Jikan jikan) {
        super(jikan);
    }

    public AnimeQueryFactory anime() {
        return new AnimeQueryFactory(this.jikan);
    }

}
