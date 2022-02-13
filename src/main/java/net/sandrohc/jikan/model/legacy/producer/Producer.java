/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.producer;

import java.util.*;

import net.sandrohc.jikan.model.legacy.base.*;
import net.sandrohc.jikan.model.legacy.season.*;

/**
 * The base object for the producer query.
 */
public class Producer extends CacheEntity {

    public MalSubEntity meta;

    public List<SeasonAnime> anime = Collections.emptyList();


    @Override
    public String toString() {
        return "Producer[id=" + meta.malId + ", name='" + meta.name + "']";
    }

}
