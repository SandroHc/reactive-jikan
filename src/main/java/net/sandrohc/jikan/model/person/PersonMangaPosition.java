/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.person;

import net.sandrohc.jikan.model.base.*;

/**
 * A person's position/role on a manga.
 */
public class PersonMangaPosition extends PersonPosition {

    public MalSubEntity manga;


    @Override
    public String toString() {
        return "PersonMangaPosition[position='" + position + "', manga=" + manga + ']';
    }

}
