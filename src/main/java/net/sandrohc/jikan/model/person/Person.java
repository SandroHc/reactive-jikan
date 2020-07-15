/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.person;

import java.time.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;

public class Person extends MalEntity {

    public String url;

    @JsonProperty("image_url")
    public String imageUrl;

    @JsonProperty("website_url")
    public String websiteUrl;

    public String name;

    @JsonProperty("given_name")
    public String givenName;

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

    public List<PersonAnimePosition> animeStaffPositions;

    public List<PersonMangaPosition> publishedManga;


    @Override
    public String toString() {
        return "Person[id=" + malId + ", name='" + name + "']";
    }

}
