/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.anime;

import java.util.*;

import net.sandrohc.jikan.model.legacy.base.*;

/**
 * The base object for the top anime query.
 */
public class AnimeTop extends CacheEntity {

    public List<AnimeTopSub> top = Collections.emptyList();


    @Override
    public String toString() {
        return "AnimeTop[top=" + (top.size() + " top") + ']';
    }

}
