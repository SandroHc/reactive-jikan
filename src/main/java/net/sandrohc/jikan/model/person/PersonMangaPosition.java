/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.person;

import java.io.*;

import net.sandrohc.jikan.model.*;

/**
 * A person's position/role on a manga.
 */
public class PersonMangaPosition implements Serializable {

    /** The position. */
    public String position;

    /** The manga. */
    public EntityWithImage manga;


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public EntityWithImage getManga() {
        return manga;
    }

    public void setManga(EntityWithImage manga) {
        this.manga = manga;
    }

    @Override
    public String toString() {
        return "PersonMangaPosition[position='" + position + "', manga=" + manga + ']';
    }
}
