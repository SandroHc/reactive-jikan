/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * A user update to an anime/manga entry.
 */
public class UserUpdateWithEntry extends UserUpdate {

    /** The anime/manga details. */
    public EntityWithImage entry;


    public EntityWithImage getEntry() {
        return entry;
    }

    public void setEntry(EntityWithImage entry) {
        this.entry = entry;
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserUpdateWithEntry that = (UserUpdateWithEntry) o;

        return entry != null ? entry.equals(that.entry) : that.entry == null;
    }

    @Generated
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (entry != null ? entry.hashCode() : 0);
        return result;
    }

    @Generated
    @Override
    public String toString() {
        return "UserUpdateWithUser[entry=" + entry + ", score=" + score + ", date=" + date + ']';
    }
}
