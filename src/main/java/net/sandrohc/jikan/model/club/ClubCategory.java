/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.club;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The club type.
 */
public enum ClubCategory {
	@JsonProperty("anime")       ANIME          ("anime"),
	@JsonProperty("manga")       MANGA          ("manga"),
	@JsonProperty("actors_and_artists") ACTORS_AND_ARTISTS("actors_and_artists"),
	@JsonProperty("characters")  CHARACTERS  ("characters"),
	@JsonProperty("cities_and_neighborhoods") CITIES_AND_NEIGHBORHOODS("cities_and_neighborhoods"),
	@JsonProperty("companies")   COMPANIES   ("companies"),
	@JsonProperty("conventions") CONVENTIONS ("conventions"),
	@JsonProperty("games")       GAMES       ("games"),
	@JsonProperty("japan")       JAPAN       ("japan"),
	@JsonProperty("music")       MUSIC       ("music"),
	@JsonProperty("other")       OTHER       ("other"),
	@JsonProperty("schools")     SCHOOLS     ("schools"),
	@JsonEnumDefaultValue        UNKNOWN     (null);


	/** Used in the search queries. */
	public final String search;

	ClubCategory(String search) {
		this.search = search;
	}

	public String getSearch() {
		return search;
	}
}
