/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model;

import net.sandrohc.jikan.utils.Generated;

/**
 * A basic MAL entity with name and count.
 */
public class EntityWithCount extends Entity {

    /** The count of results. */
    public int count;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Generated
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[id=" + malId + ", name='" + name + "', count=" + count + ']';
    }
}
