/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The person position.
 */
// TODO: find all enum values
public enum Position {
	@JsonProperty("Director") DIRECTOR,
	@JsonProperty("Producer") PRODUCER,
	@JsonEnumDefaultValue     UNKNOWN
}
