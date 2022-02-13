/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.common;

import java.util.*;

import net.sandrohc.jikan.model.legacy.base.*;

/**
 * A list of pictures.
 */
public class Pictures extends CacheEntity {

    public List<Picture> pictures = Collections.emptyList();


    @Override
    public String toString() {
        return "Pictures[pictures=" + pictures.size() + " pictures]";
    }

}
