/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonAlias("tba")
	TO_BE_AIRED,
}
