/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;
import java.time.*;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A user update to a anime/manga entry.
 */
public class UserUpdate implements Serializable {

    /** The user name. */
    public String username;

    /** The URL to the user's profile on MyAnimeList. */
    public String url;

    @JsonProperty("image_url")
    public String imageUrl;

    /** The score given by the user in the range: 0.0 (inclusive) to 10.0 (inclusive) */
    public float score;

    public String status;

    @JsonAlias({ "episodes_seen", "chapters_seen" })
    public int seen;

    @JsonAlias({ "episodes_total", "chapters_total" })
    public int total;

    public OffsetDateTime date;


    @Override
    public String toString() {
        return "UserUpdate[username='" + username + "', date=" + date + ']';
    }

}
