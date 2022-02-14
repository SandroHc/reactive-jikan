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
public enum ClubType {
	@JsonProperty("public")  PUBLIC  ("public"),
	@JsonProperty("private") PRIVATE ("private"),
	@JsonProperty("secret")  SECRET  ("secret"),
	@JsonEnumDefaultValue    UNKNOWN (null);


	/** Used in the search queries. */
	public final String search;

	ClubType(String search) {
		this.search = search;
	}

	public String getSearch() {
		return search;
	}
}
