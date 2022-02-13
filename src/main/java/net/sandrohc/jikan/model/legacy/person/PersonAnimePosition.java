/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.person;

import net.sandrohc.jikan.model.legacy.base.*;

/**
 * A person's position/role on an anime.
 */
public class PersonAnimePosition extends PersonPosition {

    public MalSubEntity anime;


    @Override
    public String toString() {
        return "PersonAnimePosition[position='" + position + "', anime=" + anime + ']';
    }

}
