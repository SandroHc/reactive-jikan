/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Type {
	@JsonProperty("anime")
	ANIME("anime", "anime"),

	@JsonProperty("manga")
	MANGA("manga", "manga"),

	@JsonProperty("person")
	@JsonAlias("people")
	PERSON("person", "people"),

	@JsonProperty("character")
	CHARACTER("character", "characters"),
	;

	/** The type used for the /search endpoint. */
	public final String search;

	/** The type used for the /top endpoint. */
	public final String top;

	Type(String search, String top) {
		this.search = search;
		this.top = top;
	}

}
