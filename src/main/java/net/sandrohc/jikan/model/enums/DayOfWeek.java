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
	MONDAY(java.time.DayOfWeek.MONDAY, "monday"),

	@JsonProperty("tuesday")
	@JsonAlias("Tuesdays")
	TUESDAY(java.time.DayOfWeek.TUESDAY, "tuesday"),

	@JsonProperty("wednesday")
	@JsonAlias("Wednesdays")
	WEDNESDAY(java.time.DayOfWeek.WEDNESDAY, "wednesday"),

	@JsonProperty("thursday")
	@JsonAlias("Thursdays")
	THURSDAY(java.time.DayOfWeek.THURSDAY, "thursday"),

	@JsonProperty("friday")
	@JsonAlias("Fridays")
	FRIDAY(java.time.DayOfWeek.FRIDAY, "friday"),

	@JsonProperty("saturday")
	@JsonAlias("Saturdays")
	SATURDAY(java.time.DayOfWeek.SATURDAY, "saturday"),

	@JsonProperty("sunday")
	@JsonAlias("Sundays")
	SUNDAY(java.time.DayOfWeek.SUNDAY, "sunday"),

	@JsonProperty("other")
	OTHER(null, "other"),

	@JsonEnumDefaultValue
	@JsonProperty("unknown")
	UNKNOWN(null, "unknown");


	/** Mapping to Java's DayOfWeek enum. */
	public final java.time.DayOfWeek javaDayOfWeek;

	/** Used in the search queries. */
	public final String search;

	DayOfWeek(java.time.DayOfWeek day, String search) {
		this.javaDayOfWeek = day;
		this.search = search;
	}

	public String getSearch() {
		return search;
	}
}
