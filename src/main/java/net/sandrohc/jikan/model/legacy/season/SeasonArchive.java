/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.season;

import java.util.*;

import net.sandrohc.jikan.model.legacy.base.*;

/**
 * A anime season year archive.
 */
public class SeasonArchive extends CacheEntity {

    public List<SeasonArchiveYear> archive = Collections.emptyList();


    @Override
    public String toString() {
        return "SeasonArchive[archive=" + archive.size() + " archives]";
    }

}
