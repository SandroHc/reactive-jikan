/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.person;

import java.io.*;

import net.sandrohc.jikan.model.*;

/**
 * A person's position/role on an anime.
 */
public class PersonAnimePosition implements Serializable {

    /** The position. */
    public String position;

    /** The anime. */
    public EntityWithImage anime;


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public EntityWithImage getAnime() {
        return anime;
    }

    public void setAnime(EntityWithImage anime) {
        this.anime = anime;
    }

    @Override
    public String toString() {
        return "PersonAnimePosition[position='" + position + "', anime=" + anime + ']';
    }
}
