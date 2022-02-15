/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.character;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.legacy.base.*;

/**
 * The object for the top character
 */
public class CharacterTopSub extends MalEntity {

    /** The character rank. */
    public int rank;

    /** The character title. */
    public String title;

    /** The URL to the character's MyAnimeList page. */
    public String url;

    /** The character image. */
    @JsonProperty("image_url")
    public String imageUrl;

    /** The character name written in it's original language. */
    @JsonProperty("name_kanji")
    public String nameKanji;

    /** The number of favourites. */
    public int favorites;

    /** The list of anime this character appeared on. */
    public List<EntityWithType> animeography;

    /** The list of manga this character appeared on. */
    public List<EntityWithType> mangaography;


    @Override
    public String toString() {
        return "CharacterTopSub[id=" + malId + ", title='" + title + "']";
    }

}
