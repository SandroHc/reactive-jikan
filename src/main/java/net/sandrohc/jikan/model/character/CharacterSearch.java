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
 * The base object for the character search query.
 */
public class CharacterSearch extends CacheEntity {

    public List<CharacterSearchSub> results = Collections.emptyList();

    @JsonProperty("last_page")
    public int lastPage;


    @Override
    public String toString() {
        return "CharacterSearch[results=" + (results.size() + " results") + ", lastPage=" + lastPage + ']';
    }

}
