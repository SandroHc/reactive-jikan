/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Available filters to sort the character search entries.
 */
public enum CharacterOrderBy {
	@JsonProperty("mal_id")    MAL_ID,
	@JsonProperty("name")      NAME,
	@JsonProperty("favorites") FAVORITES,
}
