/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.season;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An anime season.
 */
public enum Season {
	@JsonProperty("summer") SUMMER("summer"),
	@JsonProperty("spring") SPRING("spring"),
	@JsonProperty("fall")   FALL("fall"),
	@JsonProperty("winter") WINTER("winter"),

	@JsonProperty("later") LATER("later"),

	@JsonEnumDefaultValue UNKNOWN(null);


	/** Used in the search queries. */
	public final String search;

	Season(String value) {
		this.search = value;
	}

	public String getSearch() {
		return search;
	}
}
