/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.character;

import java.io.*;

import net.sandrohc.jikan.model.*;

/**
 * A relation between a character and a manga.
 */
public class CharacterManga implements Serializable {

    /** The character role in the anime. */
    public String role; // TODO: convert to enum

    /** The anime details. */
    public BasicEntity manga;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public BasicEntity getManga() {
        return manga;
    }

    public void setManga(BasicEntity manga) {
        this.manga = manga;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharacterManga that = (CharacterManga) o;

        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        return manga != null ? manga.equals(that.manga) : that.manga == null;
    }

    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + (manga != null ? manga.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Character[role=" + role + ", manga=" + manga + ']';
    }
}
