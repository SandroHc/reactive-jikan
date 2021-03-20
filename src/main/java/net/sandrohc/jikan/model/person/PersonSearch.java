/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.person;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;

/**
 * The search results for the person query.
 */
public class PersonSearch extends CacheEntity {

    public List<PersonSearchSub> results = Collections.emptyList();

    @JsonProperty("last_page")
    public int lastPage;


    @Override
    public String toString() {
        return "PersonSearch[results=" + (results.size() + " results") + ", lastPage=" + lastPage + ']';
    }

}
