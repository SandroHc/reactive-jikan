/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Available filters to sort the anime search entries.
 */
public enum AnimeOrderBy {
	@JsonProperty("mal_id")     MAL_ID,
	@JsonProperty("title")      TITLE,
	@JsonProperty("type")       TYPE,
	@JsonProperty("rating")     RATING,
	@JsonProperty("start_date") START_DATE,
	@JsonProperty("end_date")   END_DATE,
	@JsonProperty("episodes")   EPISODES,
	@JsonProperty("score")      SCORE,
	@JsonProperty("scored_by")  SCORED_BY,
	@JsonProperty("rank")       RANK,
	@JsonProperty("popularity") POPULARITY,
	@JsonProperty("members")    MEMBERS,
	@JsonProperty("favorites")  FAVORITES,
}
