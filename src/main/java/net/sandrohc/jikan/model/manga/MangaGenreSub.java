/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.manga;

import java.time.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.enums.*;

/**
 * A manga object, used by the /genre endpoint.
 */
public class MangaGenreSub extends MangaBase {

    public List<GenreEntity<MangaGenre>> genres = Collections.emptyList();

    public List<MalSubEntity> authors = Collections.emptyList();

    public List<String> serialization = Collections.emptyList();


    @JsonProperty("publishing_start")
    public void setPublishingStart(OffsetDateTime startDate) {
        published.setFrom(startDate);
    }

}
