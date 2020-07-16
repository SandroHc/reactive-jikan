/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;

public class Recommendation extends MalEntity {

    public String url;

    @JsonProperty("image_url")
    public String imageUrl;

    @JsonProperty("recommendation_url")
    public String recommendationUrl;

    public String title;

    @JsonProperty("recommendation_count")
    public int recommendationCount;


    @Override
    public String toString() {
        return "Recommendation[id=" + malId + ", title='" + title + "', recommendationCount=" + recommendationCount + ']';
    }

}
