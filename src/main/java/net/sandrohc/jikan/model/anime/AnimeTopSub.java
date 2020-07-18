/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.enums.*;

public class AnimeTopSub extends MalEntity {

    public int rank;

    public String title;

    public String url;

    @JsonProperty("image_url")
    public String imageUrl;

    public AnimeType type;

    public int episodes;

    @JsonProperty("start_date")
    public String startDate;

    @JsonProperty("end_date")
    public String endDate;

    public int members;

    public float score;


    @Override
    public String toString() {
        return "AnimeTopSub[id=" + malId + ", title='" + title + "']";
    }

}
