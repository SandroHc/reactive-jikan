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
	MONDAY("monday"),

	@JsonProperty("tuesday")
	@JsonAlias("Tuesdays")
	TUESDAY("tuesday"),

	@JsonProperty("wednesday")
	@JsonAlias("Wednesdays")
	WEDNESDAY("wednesday"),

	@JsonProperty("thursday")
	@JsonAlias("Thursdays")
	THURSDAY("thursday"),

	@JsonProperty("friday")
	@JsonAlias("Fridays")
	FRIDAY("friday"),

	@JsonProperty("saturday")
	@JsonAlias("Saturdays")
	SATURDAY("saturday"),

	@JsonProperty("sunday")
	@JsonAlias("Sundays")
	SUNDAY("sunday"),

	@JsonProperty("other")
	OTHER("other"),

	@JsonEnumDefaultValue
	@JsonProperty("unknown")
	UNKNOWN("unknown");


	/** Used in the search queries. */
	public final String search;

	DayOfWeek(String search) {
		this.search = search;
	}

	public String getSearch() {
		return search;
	}
}
