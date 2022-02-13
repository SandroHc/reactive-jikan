/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.anime;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.legacy.base.*;

/**
 * The base object for the anime by genre query.
 */
public class AnimeGenreList extends CacheEntity {

    @JsonProperty("mal_url")
    public MalSubEntity genre;

    @JsonProperty("item_count")
    public int itemCount;

    public List<AnimeGenreSub> anime = Collections.emptyList();


    @Override
    public String toString() {
        return "AnimeGenreList[anime=" + (anime.size() + " anime") + ']';
    }

}
