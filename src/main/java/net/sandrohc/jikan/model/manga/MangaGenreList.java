/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.manga;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;

/**
 * List of manga by genre.
 */
public class MangaGenreList extends CacheEntity {

    @JsonProperty("mal_url")
    public MalSubEntity genre;

    @JsonProperty("item_count")
    public int itemCount;

    public List<MangaGenreSub> manga = Collections.emptyList();


    @Override
    public String toString() {
        return "MangaGenreList[manga=" + (manga.size() + " manga") + ']';
    }

}
