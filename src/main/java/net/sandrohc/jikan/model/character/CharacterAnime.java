/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.character;

import java.io.*;

import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * A relation between a character and an anime.
 */
public class CharacterAnime implements Serializable {

    /** The character role in the anime. */
    public CharacterRole role;

    /** The anime details. */
    public EntityWithImage anime;


    public CharacterRole getRole() {
        return role;
    }

    public void setRole(CharacterRole role) {
        this.role = role;
    }

    public EntityWithImage getAnime() {
        return anime;
    }

    public void setAnime(EntityWithImage anime) {
        this.anime = anime;
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharacterAnime that = (CharacterAnime) o;

        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        return anime != null ? anime.equals(that.anime) : that.anime == null;
    }

    @Generated
    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + (anime != null ? anime.hashCode() : 0);
        return result;
    }

    @Generated
    @Override
    public String toString() {
        return "Character[role=" + role + ", anime=" + anime + ']';
    }
}
