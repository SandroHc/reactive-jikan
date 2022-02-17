/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.season.*;
import net.sandrohc.jikan.query.season.SeasonListQuery;
import net.sandrohc.jikan.query.season.SeasonNowQuery;
import net.sandrohc.jikan.query.season.SeasonQuery;
import net.sandrohc.jikan.query.season.SeasonUpcomingQuery;

/**
 * Factory for the anime season queries.
 *
 * @see <a href="https://docs.api.jikan.moe/#tag/seasons">Jikan API docs - seasons</a>
 */
public class SeasonQueryFactory extends Factory {

    public SeasonQueryFactory(Jikan jikan) {
        super(jikan);
    }

    /**
     * Get the anime released on a specific season.
     *
     * @param year The year
     * @param season The season, e.g. 'winter'
     * @return The season query
     * @see <a href="https://docs.api.jikan.moe/#operation/getSeason">Jikan API docs - getSeason</a>
     */
    public SeasonQuery get(int year, Season season) {
        return new SeasonQuery(this.jikan, year, season);
    }

    /**
     * Get the anime released on the current season.
     *
     * @return The season now query
     * @see <a href="https://docs.api.jikan.moe/#operation/getSeasonNow">Jikan API docs - getSeasonNow</a>
     */
    public SeasonNowQuery current() {
        return new SeasonNowQuery(this.jikan);
    }

    /**
     * Get the anime to be released on the upcoming season.
     *
     * @return The season now query
     * @see <a href="https://docs.api.jikan.moe/#operation/getSeasonNow">Jikan API docs - getSeasonNow</a>
     */
    public SeasonUpcomingQuery upcoming() {
        return new SeasonUpcomingQuery(this.jikan);
    }

    /**
     * Get the list of available anime seasons, by year and season.
     *
     * @return The season list query
     * @see <a href="https://docs.api.jikan.moe/#operation/getSeasonUpcoming">Jikan API docs - getSeasonUpcoming</a>
     */
    public SeasonListQuery list() {
        return new SeasonListQuery(this.jikan);
    }
}
