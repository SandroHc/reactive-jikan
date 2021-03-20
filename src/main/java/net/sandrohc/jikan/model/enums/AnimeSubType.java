/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A mix between the type of anime and if it is currently airing.
 * Used by the Top query.
 */
public enum AnimeSubType {
	@JsonProperty("airing")       AIRING,
	@JsonProperty("upcoming")     UPCOMING,
	@JsonProperty("tv")           TV,
	@JsonProperty("movie")        MOVIE,
	@JsonProperty("ova")          OVA,
	@JsonProperty("special")      SPECIAL,

	// shared with manga subtype
	@JsonProperty("bypopularity") BY_POPULARITY("bypopularity"),
	@JsonProperty("favorite")     FAVORITE,
	;

	public final String subtype;

	AnimeSubType() {
		this.subtype = this.name().toLowerCase();
	}

	AnimeSubType(String subtype) {
		this.subtype = subtype;
	}

	public String toString() {
		return subtype;
	}

}
