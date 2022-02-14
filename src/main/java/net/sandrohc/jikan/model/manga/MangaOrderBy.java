/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.manga;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Available filters to sort the manga search entries.
 */
public enum MangaOrderBy {
	@JsonProperty("mal_id")     MAL_ID("mal_id"),
	@JsonProperty("title")      TITLE("title"),
	@JsonProperty("start_date") START_DATE("start_date"),
	@JsonProperty("end_date")   END_DATE("end_date"),
	@JsonProperty("chapters")   CHAPTERS("chapters"),
	@JsonProperty("volumes")    VOLUMES("volumes"),
	@JsonProperty("score")      SCORE("score"),
	@JsonProperty("scored_by")  SCORED_BY("scored_by"),
	@JsonProperty("rank")       RANK("rank"),
	@JsonProperty("popularity") POPULARITY("popularity"),
	@JsonProperty("members")    MEMBERS("members"),
	@JsonProperty("favorites")  FAVORITES("favorites"),
	@JsonEnumDefaultValue       UNKNOWN(null);


	/** Used in the search queries. */
	public final String search;

	MangaOrderBy(String search) {
		this.search = search;
	}

	public String getSearch() {
		return search;
	}
}
