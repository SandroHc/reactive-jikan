/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;
import java.time.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * A user recommendation for an anime or manga.
 */
public class Recommendation implements Serializable {

    /** The MAL ID. */
    @JsonProperty("mal_id")
    public String malId;

    /** The recommended anime/manga's details. */
    public List<EntityWithImage> entry = Collections.emptyList();

    /** The recommendation content. */
    public String content;

    /** The date of the recommendation. */
    public OffsetDateTime date;

    /** The user that did the recommendation. */
    public UserSimple user;


    public String getMalId() {
        return malId;
    }

    public void setMalId(String malId) {
        this.malId = malId;
    }

    public void setEntry(List<EntityWithImage> entry) {
        this.entry = entry;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public UserSimple getUser() {
        return user;
    }

    public void setUser(UserSimple user) {
        this.user = user;
    }

    @Generated
    @Override
    public String toString() {
        return "Recommendation[entry=" + entry + ", content='" + content + "']";
    }
}
