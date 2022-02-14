/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.person;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Available filters to sort the person search entries.
 */
public enum PersonOrderBy {
	@JsonProperty("mal_id")    MAL_ID    ("mal_id"),
	@JsonProperty("name")      NAME      ("name"),
	@JsonProperty("birthday")  BIRTHDAY  ("birthday"),
	@JsonProperty("favorites") FAVORITES ("favorites");


	/** Used in the search queries. */
	public final String search;

	PersonOrderBy(String search) {
		this.search = search;
	}

	public String getSearch() {
		return search;
	}
}
