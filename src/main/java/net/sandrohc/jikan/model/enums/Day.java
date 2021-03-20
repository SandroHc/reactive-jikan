/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The day of the week.
 */
public enum Day {
	@JsonProperty("monday")    MONDAY,
	@JsonProperty("tuesday")   TUESDAY,
	@JsonProperty("wednesday") WEDNESDAY,
	@JsonProperty("thursday")  THURSDAY,
	@JsonProperty("friday")    FRIDAY,
	@JsonProperty("saturday")  SATURDAY,
	@JsonProperty("sunday")    SUNDAY,
	@JsonProperty("other")     OTHER,
	@JsonEnumDefaultValue
	@JsonProperty("unknown")   UNKNOWN,
}
