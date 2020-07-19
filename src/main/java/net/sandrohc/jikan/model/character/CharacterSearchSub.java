/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.character;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;

/**
 * A character object, used by the /search endpoint.
 */
public class CharacterSearchSub extends MalEntity {

    public String url;

    @JsonProperty("image_url")
    public String imageUrl;

    public String name;

    @JsonProperty("alternative_names")
    public List<String> alternativeNames = Collections.emptyList();

    public List<MalSubEntity> anime = Collections.emptyList();

    public List<MalSubEntity> manga = Collections.emptyList();


    @Override
    public String toString() {
        return "CharacterSub[id=" + malId + ", name='" + name + "']";
    }

}
