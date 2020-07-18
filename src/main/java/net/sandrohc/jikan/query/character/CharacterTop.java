/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.character;

import java.util.*;

import net.sandrohc.jikan.model.base.*;

public class CharacterTop extends CacheEntity {

    public List<CharacterTopSub> top = Collections.emptyList();


    @Override
    public String toString() {
        return "CharacterTop[top=" + (top.size() + " top") + ']';
    }

}
