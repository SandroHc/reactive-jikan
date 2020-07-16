/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.util.*;

import net.sandrohc.jikan.model.base.*;

public class UserUpdates extends CacheEntity {

    public List<UserUpdate> users = Collections.emptyList();


    @Override
    public String toString() {
        return "UserUpdates[users=" + users.size() + " users]";
    }
}
