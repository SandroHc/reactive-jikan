/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;

/**
 * Base factory class.
 */
public abstract class Factory {

    protected Jikan jikan;

    public Factory(Jikan jikan) {
        this.jikan = jikan;
    }

}
