/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A season.
 */
public enum Season {
	@JsonProperty("Summer") SUMMER,
	@JsonProperty("Spring") SPRING,
	@JsonProperty("Fall")   FALL,
	@JsonProperty("Winter") WINTER,

	@JsonProperty("Later") LATER,
}
