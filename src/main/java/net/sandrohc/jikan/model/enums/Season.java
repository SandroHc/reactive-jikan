/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Season {
	@JsonProperty("summer") SUMMER,
	@JsonProperty("spring") SPRING,
	@JsonProperty("fall")   FALL,
	@JsonProperty("winter") WINTER,
}
