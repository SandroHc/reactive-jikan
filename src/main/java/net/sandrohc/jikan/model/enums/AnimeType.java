/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type of publication for an anime.
 */
public enum AnimeType {
	@JsonProperty("All")     ALL,
	@JsonProperty("TV")      TV,
	@JsonProperty("OVA")     OVA,
	@JsonProperty("ONA")     ONA,
	@JsonProperty("Movie")   MOVIE,
	@JsonProperty("Special") SPECIAL,
	@JsonProperty("Music")   MUSIC,
	@JsonEnumDefaultValue
	@JsonAlias("-") UNKNOWN,
}
