/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.manga;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The status of a manga.
 */
public enum MangaStatus {
	@JsonProperty("Publishing")
	PUBLISHING("publishing"),

	@JsonProperty("Completed")
	@JsonAlias({ "Complete", "Finished" })
	COMPLETED("complete"),

	@JsonProperty("Hiatus")
	HIATUS("hiatus"),

	@JsonProperty("Discontinued")
	DISCONTINUED("discontinued"),

	@JsonProperty("Upcoming")
	@JsonAlias({ "TBP", "To be published" })
	UPCOMING("upcoming"),

	@JsonEnumDefaultValue
	UNKNOWN(null);


	/** Used in the search queries. */
	public final String search;

	MangaStatus(String search) {
		this.search = search;
	}

	public String getSearch() {
		return search;
	}
}
