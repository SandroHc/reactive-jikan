/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.season;

import java.time.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.legacy.base.*;
import net.sandrohc.jikan.model.legacy.enums.*;

/**
 * The details for a anime that got broadcast in the season.
 */
public class SeasonAnime extends MalEntity {

    /** The URL to the season on MyAnimeList. */
    public String url;

    /**
     * The anime official title.
     */
    public String title;

    @JsonProperty("image_url")
    public String imageUrl;

    /** The anime synopsis. */
    public String synopsis;

    /** The type of anime. */
    public AnimeType type;

    @JsonProperty("airing_start")
    public OffsetDateTime airingStart;

    /** The number of episodes. Can be {@code null} if it is unknown. */
    public Integer episodes;

    /** The number of members following this anime. */
    public int members;

    public String source;

    /** The current score, from 0.0 (inclusive) to 10.0 (inclusive). */
    public Float score;

    public boolean r18;

    public boolean kids;

    public boolean continuing;

    public List<GenreEntity<AnimeGenre>> genres = Collections.emptyList();

    public List<MalSubEntity> producers = Collections.emptyList();

    public List<String> licensors = Collections.emptyList();


    @Override
    public String toString() {
        return "SeasonAnime[id=" + malId + ", title='" + title + "']";
    }

}
