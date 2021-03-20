/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A mix between the type of manga and if it is currently publishing.
 * Used by the Top query.
 */
public enum MangaSubType {
	@JsonProperty("manga")        MANGA,
	@JsonProperty("novels")       NOVELS,
	@JsonProperty("oneshots")     ONESHOTS,
	@JsonProperty("doujin")       DOUJIN,
	@JsonProperty("manhwa")       MANHWA, // korean comics
	@JsonProperty("manhua")       MANHUA, // chinese comics

	// shared with anime subtype
	@JsonProperty("bypopularity") BY_POPULARITY("bypopularity"),
	@JsonProperty("favorite")     FAVORITE,
	;

	public final String subtype;

	MangaSubType() {
		this.subtype = this.name().toLowerCase();
	}

	MangaSubType(String subtype) {
		this.subtype = subtype;
	}

	public String toString() {
		return subtype;
	}

}
