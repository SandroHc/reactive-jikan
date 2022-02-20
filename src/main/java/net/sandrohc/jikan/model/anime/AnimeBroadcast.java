/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.io.*;

import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.utils.Generated;

public class AnimeBroadcast implements Serializable {

	/** The day of the broadcast, e.g. 'Saturdays'. */
	public DayOfWeek day; // TODO: Convert to Java's DayOfWeek?

	/** The time of the broadcast, e.g. '12:00'. */
	public String time; // TODO: Convert string to OffsetTime?

	/** The timezone of the broadcast, e.g. 'Asia/Tokyo'. See <a href="https://en.wikipedia.org/wiki/List_of_tz_database_time_zones#List">here</a> for a list of timezone names. */
	public String timezone; // TODO: Convert string to TimeZone?

	/** The summary of the broadcast, e.g. 'Saturdays at 01:00 (JST)' */
	public String string;


	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AnimeBroadcast that = (AnimeBroadcast) o;

		if (day != null ? !day.equals(that.day) : that.day != null) return false;
		if (time != null ? !time.equals(that.time) : that.time != null) return false;
		if (timezone != null ? !timezone.equals(that.timezone) : that.timezone != null) return false;
		return string != null ? string.equals(that.string) : that.string == null;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = day != null ? day.hashCode() : 0;
		result = 31 * result + (time != null ? time.hashCode() : 0);
		result = 31 * result + (timezone != null ? timezone.hashCode() : 0);
		result = 31 * result + (string != null ? string.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "AnimeBroadcast{" +
				"day='" + day + '\'' +
				", time='" + time + '\'' +
				", timezone='" + timezone + '\'' +
				", string='" + string + '\'' +
				'}';
	}
}
