/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.character;

import java.io.*;

import net.sandrohc.jikan.model.*;

/**
 * A relation between a character and an anime.
 */
public class CharacterAnime implements Serializable {

    /** The character role in the anime. */
    public String role; // TODO: convert to enum

    /** The anime details. */
    public BasicEntity anime;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public BasicEntity getAnime() {
        return anime;
    }

    public void setAnime(BasicEntity anime) {
        this.anime = anime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharacterAnime that = (CharacterAnime) o;

        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        return anime != null ? anime.equals(that.anime) : that.anime == null;
    }

    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + (anime != null ? anime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Character[role=" + role + ", anime=" + anime + ']';
    }
}
