/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.club;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Available filters to sort the club search entries.
 */
public enum ClubOrderBy {
	@JsonProperty("mal_id")         MAL_ID         ("mal_id"),
	@JsonProperty("title")          TITLE          ("title"),
	@JsonProperty("members_count")  MEMBERS_COUNT  ("members_count"),
	@JsonProperty("pictures_count") PICTURES_COUNT ("pictures_count"),
	@JsonProperty("created")        CREATED        ("created");


	/** Used in the search queries. */
	public final String search;

	ClubOrderBy(String search) {
		this.search = search;
	}

	public String getSearch() {
		return search;
	}
}
