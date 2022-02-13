/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * A user recommendation for an anime or manga.
 */
public class BasicEntity extends MalEntity {

    /** The URL to the entity. */
    public String url;

    /** The cover image of the entity. */
    public Images images;

    /** The name of the entity. */
    @JsonAlias({ "name", "title" })
    public String name;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
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
        return "BasicEntity[id=" + malId + ", name='" + name + "']";
    }
}
