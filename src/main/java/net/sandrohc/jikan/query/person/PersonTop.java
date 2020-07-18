/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.person;

import java.util.*;

import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.manga.*;

public class PersonTop extends CacheEntity {

    public List<PersonTopSub> top = Collections.emptyList();


    @Override
    public String toString() {
        return "PersonTop[top=" + (top.size() + " top") + ']';
    }

}
