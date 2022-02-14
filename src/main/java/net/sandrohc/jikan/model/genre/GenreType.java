/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.genre;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The genre type.
 */
public enum GenreType {
	@JsonProperty("genres")          GENRES          ("genres"),
	@JsonProperty("explicit_genres") EXPLICIT_GENRES ("explicit_genres"),
	@JsonProperty("themes")          THEMES          ("themes"),
	@JsonProperty("demographics")    DEMOGRAPHICS    ("demographics"),
	@JsonEnumDefaultValue            UNKNOWN         (null);


	/** Used in the search queries. */
	public final String search;

	GenreType(String search) {
		this.search = search;
	}

	public String getSearch() {
		return search;
	}
}
