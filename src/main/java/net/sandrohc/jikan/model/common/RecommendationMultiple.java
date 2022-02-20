/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;
import java.util.*;

import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * A user recommendation for an anime or manga.
 */
public class RecommendationMultiple implements Serializable {

    /** The recommended anime/manga's details. */
    public List<EntityWithImage> entry = Collections.emptyList();

    /** The URL to the recommendation. */
    public String url;

    /** The number of votes this recommendation has received. */
    public int votes;


    public List<EntityWithImage> getEntry() {
        return entry;
    }

    public void setEntry(List<EntityWithImage> entry) {
        this.entry = entry;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Generated
    @Override
    public String toString() {
        return "Recommendation[entry=" + entry + ", votes=" + votes + ']';
    }
}
