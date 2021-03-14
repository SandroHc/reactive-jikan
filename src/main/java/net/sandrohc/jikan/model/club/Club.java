/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.club;

import java.time.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;

/**
 * A Club object.
 */
public class Club extends MalEntity {

    /** The page URL on MyAnimeList. */
    public String url;

    /** The banner image URL. */
    @JsonProperty("image_url")
    public String imageUrl;

    /** The club title. */
    public String title;

    @JsonProperty("members_count")
    public int membersCount;

    @JsonProperty("pictures_count")
    public int picturesCount;

    public String category;

    public OffsetDateTime created;

    public String type;

    public List<MalSubEntity> staff = Collections.emptyList();

    @JsonProperty("anime_relations")
    public List<MalSubEntity> animeRelations = Collections.emptyList();

    @JsonProperty("manga_relations")
    public List<MalSubEntity> mangaRelations = Collections.emptyList();

    @JsonProperty("character_relations")
    public List<MalSubEntity> characterRelations = Collections.emptyList();


    @Override
    public String toString() {
        return "Club[id=" + malId + ", title='" + title + "']";
    }

}
