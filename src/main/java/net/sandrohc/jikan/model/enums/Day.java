/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Day {
	@JsonProperty("monday")    MONDAY,
	@JsonProperty("tuesday")   TUESDAY,
	@JsonProperty("wednesday") WEDNESDAY,
	@JsonProperty("thursday")  THURSDAY,
	@JsonProperty("friday")    FRIDAY,
	@JsonProperty("saturday")  SATURDAY,
	@JsonProperty("sunday")    SUNDAY,
	@JsonProperty("other")     OTHER,
	@JsonProperty("unknown")   UNKNOWN,
}
