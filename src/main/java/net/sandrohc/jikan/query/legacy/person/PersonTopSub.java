/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.person;

import java.time.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;

public class PersonTopSub extends MalEntity {

    public int rank;

    public String title;

    public String url;

    @JsonProperty("image_url")
    public String imageUrl;

    @JsonProperty("name_kanji")
    public String nameKanji;

    public int favorites;

    public OffsetDateTime birthday;


    @Override
    public String toString() {
        return "PersonTopSub[id=" + malId + ", title='" + title + "']";
    }

}
