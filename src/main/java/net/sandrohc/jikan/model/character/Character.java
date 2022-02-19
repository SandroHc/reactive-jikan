/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.character;

import java.util.*;

import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;

/**
 * A character with all its details.
 */
public class Character extends MalEntity {

    /** The URL to the character on MyAnimeList. */
    public String url;

    /** The banner image. */
    public Images images;

    /** The official name. */
    public String name;

    /** The list of nicknames. */
    public Collection<String> nicknames = Collections.emptyList();

    /** The number of users with this character in their favourites list. */
    public int favorites;

    /** A summary about the character. */
    public String about;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<String> getNicknames() {
        return nicknames;
    }

    public void setNicknames(Collection<String> nicknames) {
        this.nicknames = nicknames;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return "Character[id=" + malId + ", name='" + name + "']";
    }
}
