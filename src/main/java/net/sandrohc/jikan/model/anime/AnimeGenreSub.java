/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.time.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.enums.*;

public class AnimeGenreSub extends MalEntity {

    public String title;

    public String url;

    @JsonProperty("image_url")
    public String imageUrl;

    public String synopsis;

    public AnimeType type;

    @JsonProperty("airing_start")
    public OffsetDateTime airingStart;

    public int episodes;

    public int members;

    public String source;

    public boolean r18;

    public boolean kids;

    public float score;

    public List<MalSubEntity> genres = Collections.emptyList();

    public List<MalSubEntity> producers = Collections.emptyList();

    public List<String> licensors = Collections.emptyList();


    @Override
    public String toString() {
        return "AnimeGenreSub[id=" + malId + ", title='" + title + "']";
    }

}
