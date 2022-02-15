/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import java.io.*;
import java.util.*;

import net.sandrohc.jikan.utils.Generated;

/**
 * A user update with the latest anime and manga entries.
 */
public class UserUpdates implements Serializable {

    /** The last three anime updates. */
    public Collection<UserUpdateWithEntry> anime = Collections.emptyList();

    /** The last three manga updates. */
    public Collection<UserUpdateWithEntry> manga = Collections.emptyList();


    public Collection<UserUpdateWithEntry> getAnime() {
        return anime;
    }

    public void setAnime(Collection<UserUpdateWithEntry> anime) {
        this.anime = anime;
    }

    public Collection<UserUpdateWithEntry> getManga() {
        return manga;
    }

    public void setManga(Collection<UserUpdateWithEntry> manga) {
        this.manga = manga;
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserUpdates that = (UserUpdates) o;

        if (anime != null ? !anime.equals(that.anime) : that.anime != null) return false;
        return manga != null ? manga.equals(that.manga) : that.manga == null;
    }

    @Generated
    @Override
    public int hashCode() {
        int result = anime != null ? anime.hashCode() : 0;
        result = 31 * result + (manga != null ? manga.hashCode() : 0);
        return result;
    }

    @Generated
    @Override
    public String toString() {
        return "UserUpdates[anime=" + anime + ", manga=" + manga + ']';
    }
}
