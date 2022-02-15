/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model;

import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * A basic MAL entity with name and type.
 */
public class EntityWithType extends Entity {

    /** The entity type, e.g. 'anime'. */
    public Type type;


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Generated
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[id=" + malId + ", name='" + name + "', type=" + type + ']';
    }
}
