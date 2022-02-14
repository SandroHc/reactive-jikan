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
 * The official age rating.
 */
public enum AgeRating {
	@JsonProperty("G")
	@JsonAlias("G - All Ages")
	G("g"),

	@JsonProperty("PG")
	@JsonAlias("PG - Children")
	PG("pg"),

	@JsonProperty("PG-13")
	@JsonAlias("PG-13 - Teens 13 or older")
	PG13("pg13"),

	@JsonProperty("R")
	@JsonAlias({
			"R - 17+ (violence & profanity)",
			"R - 17+ recommended (violence & profanity)",
	})
	R17("r17"),

	@JsonProperty("R+")
	@JsonAlias({
			"R+ - Mild Nudity",
			"R+ - Mild Nudity (may also contain violence & profanity)"
	})
	R("r"),

	@JsonProperty("Rx")
	@JsonAlias("Rx - Hentai (extreme sexual content/nudity)")
	RX("rx"),

	@JsonEnumDefaultValue
	UNKNOWN(null);


	/** Used in the search queries. */
	public final String search;

	AgeRating(String search) {
		this.search = search;
	}

	public String getSearch() {
		return search;
	}
}
