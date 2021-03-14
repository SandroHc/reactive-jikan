/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.time.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.enums.*;

/**
 * An anime or manga review.
 */
public class Review extends MalEntity {

    public String url;

    public Type type;

    @JsonProperty("helpful_count")
    public int helpfulCount;

    public OffsetDateTime date;

    public Reviewer reviewer;

    public String content;


    @Override
    public String toString() {
        return "Review[malId=" + malId + ", reviewer=" + reviewer + ", helpfulCount=" + helpfulCount + ", date=" + date + ']';
    }

}
