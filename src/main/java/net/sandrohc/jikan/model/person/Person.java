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

/**
 * A person object with all its details.
 */
public class Person extends EntityWithImage {

    /** The official website URL. */
    @JsonProperty("website_url")
    public String websiteUrl;

    /** The forename, or given (first) name. */
    @JsonProperty("given_name")
    public String givenName;

    /** The surname, or family (last) name. */
    @JsonProperty("family_name")
    public String familyName;

    /** The alternative names. */
    @JsonProperty("alternate_names")
    public Collection<String> alternativeNames = Collections.emptyList();

    /** The birthday. Will be {@code null} if unknown. */
    public OffsetDateTime birthday;

    /** The number of users that have added this person to their favourites. */
    public int favorites;

    /** A small description of this person. */
    public String about;


    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Collection<String> getAlternativeNames() {
        return alternativeNames;
    }

    public void setAlternativeNames(Collection<String> alternativeNames) {
        this.alternativeNames = alternativeNames;
    }

    public OffsetDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(OffsetDateTime birthday) {
        this.birthday = birthday;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return "Person[id=" + malId + ", name='" + name + "']";
    }
}
