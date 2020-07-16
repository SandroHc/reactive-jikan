/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.time.*;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserUpdate {

    public String username;

    public String url;

    @JsonProperty("image_url")
    public String imageUrl;

    public float score; // range [0.0, 10.0]

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
