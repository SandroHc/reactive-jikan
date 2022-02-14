/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Available filters to sort the anime search entries.
 */
public enum AnimeOrderBy {
	@JsonProperty("mal_id")     MAL_ID     ("mal_id"),
	@JsonProperty("title")      TITLE      ("title"),
	@JsonProperty("type")       TYPE       ("type"),
	@JsonProperty("rating")     RATING     ("rating"),
	@JsonProperty("start_date") START_DATE ("start_date"),
	@JsonProperty("end_date")   END_DATE   ("end_date"),
	@JsonProperty("episodes")   EPISODES   ("episodes"),
	@JsonProperty("score")      SCORE      ("score"),
	@JsonProperty("scored_by")  SCORED_BY  ("scored_by"),
	@JsonProperty("rank")       RANK       ("rank"),
	@JsonProperty("popularity") POPULARITY ("popularity"),
	@JsonProperty("members")    MEMBERS    ("members"),
	@JsonProperty("favorites")  FAVORITES  ("favorites");


	/** Used in the search queries. */
	public final String search;

	AnimeOrderBy(String search) {
		this.search = search;
	}

	public String getSearch() {
		return search;
	}
}
