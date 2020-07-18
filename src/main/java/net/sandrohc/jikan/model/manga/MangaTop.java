/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.manga;

import java.util.*;

import net.sandrohc.jikan.model.base.*;

public class MangaTop extends CacheEntity {

    public List<MangaTopSub> top = Collections.emptyList();


    @Override
    public String toString() {
        return "MangaTop[top=" + (top.size() + " top") + ']';
    }

}
