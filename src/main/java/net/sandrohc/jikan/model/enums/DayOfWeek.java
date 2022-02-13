/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The day of the week.
 */
public enum DayOfWeek {
	@JsonProperty("monday")
	@JsonAlias("Mondays")
	MONDAY,

	@JsonProperty("tuesday")
	@JsonAlias("Tuesdays")
	TUESDAY,

	@JsonProperty("wednesday")
	@JsonAlias("Wednesdays")
	WEDNESDAY,

	@JsonProperty("thursday")
	@JsonAlias("Thursdays")
	THURSDAY,

	@JsonProperty("friday")
	@JsonAlias("Fridays")
	FRIDAY,

	@JsonProperty("saturday")
	@JsonAlias("Saturdays")
	SATURDAY,

	@JsonProperty("sunday")
	@JsonAlias("Sundays")
	SUNDAY,

	@JsonProperty("other")
	OTHER,

	@JsonEnumDefaultValue
	@JsonProperty("unknown")   UNKNOWN,
}
