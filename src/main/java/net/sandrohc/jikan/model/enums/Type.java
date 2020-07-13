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
	ANIME,

	@JsonProperty("manga")
	MANGA,

	@JsonProperty("person")
	@JsonAlias("people")
	PERSON,

	@JsonProperty("character")
	CHARACTER,
}
