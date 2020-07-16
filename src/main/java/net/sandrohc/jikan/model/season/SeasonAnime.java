/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.season;

import java.time.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.enums.*;

public class SeasonAnime extends MalEntity {

    public String url;

    public String title;

    @JsonProperty("image_url")
    public String imageUrl;

    public String synopsis;

    public AnimeType type;

    @JsonProperty("airing_start")
    public OffsetDateTime airingStart;

    public Integer episodes;

    public int members;

    public List<GenreEntity<AnimeGenre>> genres = Collections.emptyList();

    public String source; // TODO: convert to enum. { "Light novel", "Manga" }

    public Float score;

    public List<MalSubEntity> producers = Collections.emptyList();

    public List<String> licensors = Collections.emptyList();

    public boolean r18;

    public boolean kids;

    public boolean continuing;


    @Override
    public String toString() {
        return "SeasonAnime[id=" + malId + ", title='" + title + "']";
    }

}
