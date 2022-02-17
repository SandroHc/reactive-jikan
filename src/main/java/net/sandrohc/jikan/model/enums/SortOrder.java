/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The sort order. Used by the search queries.
 */
public enum SortOrder {
	@JsonProperty("ascending")
	@JsonAlias("asc")
	ASCENDING("asc"),

	@JsonProperty("descending")
	@JsonAlias("desc")
	DESCENDING("desc");


	/** Used in the search queries. */
	public final String search;

	SortOrder(String value) {
		this.search = value;
	}

	public String getSearch() {
		return search;
	}
}
