/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.person;

import java.time.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;

/**
 * A person object with all its details.
 */
public class Person extends MalEntity {

    /** The URL to the person on MyAnimeList. */
    public String url;

    /** The banner image URL. */
    @JsonProperty("image_url")
    public String imageUrl;

    /** The official website URL. */
    @JsonProperty("website_url")
    public String websiteUrl;

    /** The name. */
    public String name;

    /** The given (first) name. */
    @JsonProperty("given_name")
    public String givenName;

    /** The family (last) name. */
    @JsonProperty("family_name")
    public String familyName;

    @JsonProperty("alternative_names")
    public List<String> alternativeNames;

    public OffsetDateTime birthday;

    @JsonProperty("member_favourites")
    public int memberFavourites;

    public String about;

    @JsonProperty("voice_acting_roles")
    public List<PersonVoiceActingRole> voiceActingRoles;

    @JsonProperty("anime_staff_positions")
    public List<PersonAnimePosition> animeStaffPositions;

    @JsonProperty("published_manga")
    public List<PersonMangaPosition> publishedManga;


    @Override
    public String toString() {
        return "Person[id=" + malId + ", name='" + name + "']";
    }

}
