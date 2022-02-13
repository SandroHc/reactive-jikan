/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.person;

import java.util.*;

import net.sandrohc.jikan.model.legacy.base.*;

public class PersonTop extends CacheEntity {

    public List<PersonTopSub> top = Collections.emptyList();


    @Override
    public String toString() {
        return "PersonTop[top=" + (top.size() + " top") + ']';
    }

}
