/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.season;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.legacy.base.*;
import net.sandrohc.jikan.model.legacy.enums.Season;

/**
 * A season and the list of anime that got broadcast in it.
 */
public class SeasonList extends CacheEntity {

    @JsonProperty("season_name")
    public Season season;

    @JsonProperty("season_year")
    public int seasonYear;

    public List<SeasonAnime> anime = Collections.emptyList();


    @Override
    public String toString() {
        return "Season[season=" + season + ", year=" + seasonYear + ']';
    }

}
