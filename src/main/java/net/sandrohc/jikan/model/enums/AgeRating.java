/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum AgeRating {
	@JsonProperty("G")
	@JsonAlias("G - All Ages")
	G,

	@JsonProperty("PG")
	@JsonAlias("PG - Children")
	PG,

	@JsonProperty("PG-13")
	@JsonAlias("PG-13 - Teens 13 or older")
	PG13,

	@JsonProperty("R")
	@JsonAlias({
			"R - 17+ (violence & profanity)",
			"R - 17+ recommended (violence & profanity)",
	})
	R17,

	@JsonProperty("R+")
	@JsonAlias("R+ - Mild Nudity (may also contain violence & profanity)")
	R,

	@JsonProperty("Rx")
	@JsonAlias("Rx - Hentai (extreme sexual content/nudity)")
	RX,
}
