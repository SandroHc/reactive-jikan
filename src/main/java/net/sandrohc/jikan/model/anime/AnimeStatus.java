/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The status of an anime.
 */
public enum AnimeStatus {
	@JsonProperty("Airing")
	@JsonAlias("Currently Airing")
	AIRING("airing"),

	@JsonProperty("Completed")
	@JsonAlias({ "Complete", "Finished Airing" })
	COMPLETED("complete"),

	@JsonProperty("Upcoming")
	@JsonAlias({
			"tba",
			"to_be_aired",
			"to be aired",
			"Not yet aired"
	})
	UPCOMING("upcoming"),

	@JsonEnumDefaultValue
	UNKNOWN(null);


	/** Used in the search queries. */
	public final String search;

	AnimeStatus(String search) {
		this.search = search;
	}

	public String getSearch() {
		return search;
	}
}
