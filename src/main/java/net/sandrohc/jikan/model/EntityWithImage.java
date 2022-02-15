/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model;

import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * A basic MAL entity with name and image.
 */
public class EntityWithImage extends Entity {

    /** The cover image of the entity. */
    public Images images;


    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    @Generated
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[id=" + malId + ", name='" + name + "', images=" + images + "]";
    }
}
