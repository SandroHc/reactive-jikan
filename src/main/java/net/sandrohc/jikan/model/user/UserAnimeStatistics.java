/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import java.io.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.utils.Generated;

/**
 * The anime statistics of a user.
 */
public class UserAnimeStatistics implements Serializable {

	/** The number of days of anime watched. */
	@JsonProperty("days_watched")
	public double daysWatched; // TODO: convert to TemporalAmount

	/** The mean score. */
	@JsonProperty("mean_score")
	public double meanScore;

	/** The number of anime the user is currently watching. */
	public int watching;

	/** The number of anime the user has watched/completed. */
	public int completed;

	/** The number of anime the user has put on-hold. */
	@JsonProperty("on_hold")
	public int onHold;

	/** The number of anime the user has dropped. */
	public int dropped;

	/** The number of anime the user is planning to watch. */
	@JsonProperty("plan_to_watch")
	public int planToWatch;

	/** The total number of anime entries. */
	@JsonProperty("total_entries")
	public int totalEntries;

	/** The number of anime rewatched. */
	public int rewatched;

	/** The total number of episodes watched. */
	@JsonProperty("episodes_watched")
	public int episodesWatched;


	public double getDaysWatched() {
		return daysWatched;
	}

	public void setDaysWatched(double daysWatched) {
		this.daysWatched = daysWatched;
	}

	public double getMeanScore() {
		return meanScore;
	}

	public void setMeanScore(double meanScore) {
		this.meanScore = meanScore;
	}

	public int getWatching() {
		return watching;
	}

	public void setWatching(int watching) {
		this.watching = watching;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public int getOnHold() {
		return onHold;
	}

	public void setOnHold(int onHold) {
		this.onHold = onHold;
	}

	public int getDropped() {
		return dropped;
	}

	public void setDropped(int dropped) {
		this.dropped = dropped;
	}

	public int getPlanToWatch() {
		return planToWatch;
	}

	public void setPlanToWatch(int planToWatch) {
		this.planToWatch = planToWatch;
	}

	public int getTotalEntries() {
		return totalEntries;
	}

	public void setTotalEntries(int totalEntries) {
		this.totalEntries = totalEntries;
	}

	public int getRewatched() {
		return rewatched;
	}

	public void setRewatched(int rewatched) {
		this.rewatched = rewatched;
	}

	public int getEpisodesWatched() {
		return episodesWatched;
	}

	public void setEpisodesWatched(int episodesWatched) {
		this.episodesWatched = episodesWatched;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserAnimeStatistics that = (UserAnimeStatistics) o;

		if (Double.compare(that.daysWatched, daysWatched) != 0) return false;
		if (Double.compare(that.meanScore, meanScore) != 0) return false;
		if (watching != that.watching) return false;
		if (completed != that.completed) return false;
		if (onHold != that.onHold) return false;
		if (dropped != that.dropped) return false;
		if (planToWatch != that.planToWatch) return false;
		if (totalEntries != that.totalEntries) return false;
		if (rewatched != that.rewatched) return false;
		return episodesWatched == that.episodesWatched;
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

	@Generated
	@Override
	public String toString() {
		return "UserAnimeStatistics[" +
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
