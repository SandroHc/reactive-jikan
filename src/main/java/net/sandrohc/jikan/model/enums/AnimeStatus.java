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
 * The status of an anime.
 */
public enum AnimeStatus {
	@JsonProperty("Airing")
	@JsonAlias("Currently Airing")
	AIRING,

	@JsonProperty("Completed")
	@JsonAlias({ "Complete", "Finished Airing" })
	COMPLETED,

	@JsonProperty("Upcoming")
	UPCOMING,

	@JsonProperty("to_be_aired")
	@JsonAlias({
			"tba",
			"to be aired",
			"Not yet aired"
	})
	TO_BE_AIRED,

	@JsonEnumDefaultValue
	@JsonProperty("Unknown")
	UNKNOWN,
}
