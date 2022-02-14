/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * A basic MAL entity with name and type.
 */
public class EntityWithType extends MalEntity {

    /** The URL to the entity page. */
    public String url;

    /** The entity type, e.g. 'anime'. */
    public Type type;

    /** The entity name. */
    @JsonAlias({ "name", "title" })
    public String name;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Generated
    @Override
    public String toString() {
        return "EntityWithType[id=" + malId + ", name='" + name + "', type='" + type + "']";
    }
}
