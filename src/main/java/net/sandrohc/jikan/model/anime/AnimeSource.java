/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The anime source.
 */
// TODO: find all enum values
public enum AnimeSource {
	@JsonProperty("Original")    ORIGINAL,
	@JsonProperty("Manga")       MANGA,
	@JsonProperty("Light novel") LIGHT_NOVEL,
	@JsonEnumDefaultValue        UNKNOWN
}
