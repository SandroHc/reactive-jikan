/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.person;

import java.io.*;

import com.fasterxml.jackson.annotation.JsonAlias;
import net.sandrohc.jikan.model.*;

/**
 * A person's role/position on an anime/manga.
 */
public class PersonRole implements Serializable {

    /** The role. */
    @JsonAlias("position")
    public String role;

    /** The anime/manga. */
    @JsonAlias({ "anime", "manga" })
    public EntityWithImage entry;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public EntityWithImage getEntry() {
        return entry;
    }

    public void setEntry(EntityWithImage entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return "PersonMangaPosition[position='" + role + "', manga=" + entry + ']';
    }
}
