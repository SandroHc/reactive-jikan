/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The meta type.
 */
public enum Type {
	@JsonProperty("anime")
	ANIME("anime", "anime", "anime"),

	@JsonProperty("manga")
	MANGA("manga", "manga", "manga"),

	@JsonProperty("person")
	@JsonAlias("people")
	PERSON("person", null, "people"),

	@JsonProperty("character")
	CHARACTER("character", null, "characters"),

	@JsonProperty("profile")
	PROFILE(null, null, null),

	@JsonEnumDefaultValue
	UNKNOWN(null, null, null),
	;

	/** The type used for the /search endpoint. */
	public final String search;

	/** The type used for the /top endpoint. */
	public final String top;

	/** The type used for the /genre endpoint. */
	public final String genre;

	Type(String search, String genre, String top) {
		this.search = search;
		this.genre = genre;
		this.top = top;
	}

}
