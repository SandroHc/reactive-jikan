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
 * The anime stats of a MAL user.
 */
public class UserAnimeStats implements Serializable {

	@JsonProperty("days_watched")
	public double daysWatched;

	@JsonProperty("mean_score")
	public double meanScore;

	public int watching;

	public int completed;

	@JsonProperty("on_hold")
	public int onHold;

	public int dropped;

	@JsonProperty("plan_to_watch")
	public int planToWatch;

	@JsonProperty("total_entries")
	public int totalEntries;

	public int rewatched;

	@JsonProperty("episodes_watched")
	public int episodesWatched;


	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserAnimeStats that = (UserAnimeStats) o;

		return totalEntries == that.totalEntries
			&& episodesWatched == that.episodesWatched
			&& watching == that.watching
			&& completed == that.completed
			&& onHold == that.onHold
			&& dropped == that.dropped
			&& planToWatch == that.planToWatch
			&& rewatched == that.rewatched
			&& Double.compare(that.daysWatched, daysWatched) == 0
			&& Double.compare(that.meanScore, meanScore) == 0;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		long temp;
		temp = Double.doubleToLongBits(daysWatched);
		result = (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(meanScore);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + watching;
		result = 31 * result + completed;
		result = 31 * result + onHold;
		result = 31 * result + dropped;
		result = 31 * result + planToWatch;
		result = 31 * result + totalEntries;
		result = 31 * result + rewatched;
		result = 31 * result + episodesWatched;
		return result;
	}

	@Override
	public String toString() {
		return "UserAnimeStats[" +
				"totalEntries=" + totalEntries +
				", daysWatched=" + daysWatched +
				", episodesWatched=" + episodesWatched +
				", meanScore=" + meanScore +
				", watching=" + watching +
				", completed=" + completed +
				", onHold=" + onHold +
				", dropped=" + dropped +
				", planToWatch=" + planToWatch +
				", rewatched=" + rewatched +
				']';
	}
}
