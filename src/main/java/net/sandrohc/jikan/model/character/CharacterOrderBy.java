/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.character;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Available filters to sort the character search entries.
 */
public enum CharacterOrderBy {
	@JsonProperty("mal_id")    MAL_ID    ("mal_id"),
	@JsonProperty("name")      NAME      ("name"),
	@JsonProperty("favorites") FAVORITES ("favorites");


	/** Used in the search queries. */
	public final String search;

	CharacterOrderBy(String search) {
		this.search = search;
	}

	public String getSearch() {
		return search;
	}
}
