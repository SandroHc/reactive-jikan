/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.person;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;

/**
 * A person object, used by the /search endpoint.
 */
public class PersonSearchSub extends MalEntity {

    /** The page URL on MyAnimeList. */
    public String url;

    @JsonProperty("image_url")
    public String imageUrl;

    public String name;

    @JsonProperty("alternative_names")
    public List<String> alternativeNames;


    @Override
    public String toString() {
        return "PersonSub[id=" + malId + ", name='" + name + "']";
    }

}
