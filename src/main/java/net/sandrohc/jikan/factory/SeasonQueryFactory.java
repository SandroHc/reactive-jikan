/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.season.SeasonArchiveQuery;
import net.sandrohc.jikan.query.season.SeasonLaterQuery;
import net.sandrohc.jikan.query.season.SeasonQuery;

public class SeasonQueryFactory extends Factory {

    public SeasonQueryFactory(Jikan jikan) {
        super(jikan);
    }

    public SeasonQuery get(int year, Season season) {
        return new SeasonQuery(this.jikan, year, season);
    }

    public SeasonArchiveQuery archive() {
        return new SeasonArchiveQuery(this.jikan);
    }

    public SeasonLaterQuery later() {
        return new SeasonLaterQuery(this.jikan);
    }

}
