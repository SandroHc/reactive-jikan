/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A reviewer.
 */
public class Reviewer implements Serializable {

    public String url;

    @JsonProperty("image_url")
    public String imageUrl;

    public String username;

    @JsonAlias({ "episodes_seen", "chapters_read" })
    public int read;

    public ReviewerScores scores;


    @Override
    public String toString() {
        return "Reviewer[username='" + username + "']";
    }

}
