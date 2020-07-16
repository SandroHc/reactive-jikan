/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.util.*;

import net.sandrohc.jikan.model.base.*;

public class Reviews extends CacheEntity {

    public List<Review> reviews = Collections.emptyList();


    @Override
    public String toString() {
        return "Reviews[reviews=" + reviews.size() + " reviews]";
    }
}
