/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.manga;

import java.time.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.enums.*;

public class MangaGenreSub extends MalEntity {

    public String title;

    public String url;

    @JsonProperty("image_url")
    public String imageUrl;

    public String synopsis;

    public MangaType type;

    @JsonProperty("publishing_start")
    public OffsetDateTime publishingStart;

    public int volumes;

    public float score;

    public int members;

    public List<GenreEntity<MangaGenre>> genres = Collections.emptyList();

    public List<MalSubEntity> authors = Collections.emptyList();

    public List<String> serialization = Collections.emptyList();


    @Override
    public String toString() {
        return "MangaGenreSub[id=" + malId + ", title='" + title + "']";
    }

}
