/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory.legacy;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.factory.Factory;
import net.sandrohc.jikan.model.legacy.enums.*;
import net.sandrohc.jikan.query.legacy.season.SeasonArchiveQuery;
import net.sandrohc.jikan.query.legacy.season.SeasonLaterQuery;
import net.sandrohc.jikan.query.legacy.season.SeasonQuery;

/**
 * Factory for the season queries.
 */
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
