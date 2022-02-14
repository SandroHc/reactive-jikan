/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.person;

import java.time.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;

/**
 * A person object with all its details.
 */
public class Person extends MalEntity {

    /** The URL to the person on MyAnimeList. */
    public String url;

    /** The official website URL. */
    @JsonProperty("website_url")
    public String websiteUrl;

    /** The banner image. */
    public Images images;

    /** The person name. */
    public String name;

    /** The given (first) name. */
    @JsonProperty("given_name")
    public String givenName;

    /** The family (last) name. */
    @JsonProperty("family_name")
    public String familyName;

    /** The alternative names. */
    @JsonProperty("alternative_names")
    public List<String> alternativeNames;

    /** The birthday. Will be {@code null} if unknown. */
    public OffsetDateTime birthday;

    /** The number of users that have added this person to their favourites. */
    public int favourites;

    /** A small description of this person. */
    public String about;

//    @JsonProperty("voice_acting_roles")
//    public List<PersonVoiceActingRole> voiceActingRoles;
//
//    @JsonProperty("anime_staff_positions")
//    public List<PersonAnimePosition> animeStaffPositions;
//
//    @JsonProperty("published_manga")
//    public List<PersonMangaPosition> publishedManga;


    @Override
    public String toString() {
        return "Person[id=" + malId + ", name='" + name + "']";
    }
}
