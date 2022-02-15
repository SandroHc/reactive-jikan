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
 *
 * @see <a href="https://docs.api.jikan.moe/#tag/seasons">Jikan API docs - seasons</a>
 */
public class SeasonQueryFactory extends Factory {

    public SeasonQueryFactory(Jikan jikan) {
        super(jikan);
    }

    /**
     * Get the season details.
     *
     * @param year The year
     * @param season The season
     * @return The season query factory
     * @see <a href="https://docs.api.jikan.moe/#operation/getSeason">Jikan API docs - getSeason</a>
     */
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
