/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;

/**
 * A user recommendation for an anime or manga.
 */
public class Recommendation extends MalEntity {

    /** The URL to the anime/manga recommended. */
    public String url;

    /** The image. */
    @JsonProperty("image_url")
    public String imageUrl;

    /** The URL for this recommendation's MyAnimeList page. */
    @JsonProperty("recommendation_url")
    public String recommendationUrl;

    /** The title of the recommendation. */
    public String title;

    /** The number of times this recommendation has been made. */
    @JsonProperty("recommendation_count")
    public int recommendationCount;


    @Override
    public String toString() {
        return "Recommendation[id=" + malId + ", title='" + title + "', recommendationCount=" + recommendationCount + ']';
    }

}
