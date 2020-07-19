/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.time.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;

/**
 * An anime object, used by the /genre endpoint.
 */
public class AnimeGenreSub extends AnimeBase {

    public String source;

    public boolean r18;

    public boolean kids;

    public List<MalSubEntity> genres = Collections.emptyList();

    public List<MalSubEntity> producers = Collections.emptyList();

    public List<String> licensors = Collections.emptyList();


    @JsonProperty("airing_start")
    public void setAiringStart(OffsetDateTime startDate) {
        aired.setFrom(startDate);
    }

}
