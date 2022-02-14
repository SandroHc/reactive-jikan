/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import net.sandrohc.jikan.utils.Generated;

/**
 * A basic MAL entity with name and count.
 */
// TODO: create dummy classes, like 'Magazine', or 'Genre'
public class EntityWithCount extends MalEntity {

    /** The URL to the entity page. */
    public String url;

    /** The entity name. */
    @JsonAlias({ "name", "title" })
    public String name;

    /** The count of results. */
    public int count;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setType(int count) {
        this.count = count;
    }

    @Generated
    @Override
    public String toString() {
        return "EntityWithCount[id=" + malId + ", name='" + name + "', count=" + count + ']';
    }
}
