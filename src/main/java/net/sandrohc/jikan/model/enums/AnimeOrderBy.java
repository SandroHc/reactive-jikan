/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AnimeOrderBy {
	@JsonProperty("title")      TITLE,
	@JsonProperty("start_date") START_DATE,
	@JsonProperty("end_date")   END_DATE,
	@JsonProperty("score")      SCORE,
	@JsonProperty("type")       TYPE,
	@JsonProperty("members")    MEMBERS,
	@JsonProperty("id")         ID,
	@JsonProperty("episodes")   EPISODES,
	@JsonProperty("rating")     RATING,
}
