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
 * A relation between a character and a manga.
 */
public class CharacterManga implements Serializable {

    /** The character role in the anime. */
    public CharacterRole role;

    /** The anime details. */
    public EntityWithImage manga;


    public CharacterRole getRole() {
        return role;
    }

    public void setRole(CharacterRole role) {
        this.role = role;
    }

    public EntityWithImage getManga() {
        return manga;
    }

    public void setManga(EntityWithImage manga) {
        this.manga = manga;
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharacterManga that = (CharacterManga) o;

        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        return manga != null ? manga.equals(that.manga) : that.manga == null;
    }

    @Generated
    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + (manga != null ? manga.hashCode() : 0);
        return result;
    }

    @Generated
    @Override
    public String toString() {
        return "Character[role=" + role + ", manga=" + manga + ']';
    }
}
