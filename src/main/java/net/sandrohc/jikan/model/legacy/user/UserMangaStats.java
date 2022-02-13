/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.user;

import java.io.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.utils.Generated;

/**
 * The manga stats of a MAL user.
 */
public class UserMangaStats implements Serializable {

	@JsonProperty("days_read")
	public double daysRead;

	@JsonProperty("mean_score")
	public double meanScore;

	public int reading;

	public int completed;

	@JsonProperty("on_hold")
	public int onHold;

	public int dropped;

	@JsonProperty("plan_to_read")
	public int planToRead;

	@JsonProperty("total_entries")
	public int totalEntries;

	public int reread;

	@JsonProperty("chapters_read")
	public int chaptersRead;

	@JsonProperty("volumes_read")
	public int volumesRead;


	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserMangaStats that = (UserMangaStats) o;

		return totalEntries == that.totalEntries
			&& chaptersRead == that.chaptersRead
			&& volumesRead == that.volumesRead
			&& reading == that.reading
			&& completed == that.completed
			&& onHold == that.onHold
			&& dropped == that.dropped
			&& planToRead == that.planToRead
			&& reread == that.reread
			&& Double.compare(that.daysRead, daysRead) == 0
			&& Double.compare(that.meanScore, meanScore) == 0;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		long temp;
		temp = Double.doubleToLongBits(daysRead);
		result = (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(meanScore);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + reading;
		result = 31 * result + completed;
		result = 31 * result + onHold;
		result = 31 * result + dropped;
		result = 31 * result + planToRead;
		result = 31 * result + totalEntries;
		result = 31 * result + reread;
		result = 31 * result + chaptersRead;
		return result;
	}

	@Override
	public String toString() {
		return "UserAnimeStats[" +
				"totalEntries=" + totalEntries +
				", daysRead=" + daysRead +
				", chaptersRead=" + chaptersRead +
				", volumesRead=" + volumesRead +
				", meanScore=" + meanScore +
				", reading=" + reading +
				", completed=" + completed +
				", onHold=" + onHold +
				", dropped=" + dropped +
				", planToRead=" + planToRead +
				", reread=" + reread +
				']';
	}
}
