/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.magazine;

import java.util.*;

import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.manga.*;

/**
 * A magazine, and a list of manga published on this magazine.
 */
public class Magazine extends CacheEntity {

    public MalSubEntity meta;

    public List<MangaGenreSub> manga = Collections.emptyList();


    @Override
    public String toString() {
        return "Magazine[id=" + meta.malId + ", name='" + meta.name + "']";
    }

}
