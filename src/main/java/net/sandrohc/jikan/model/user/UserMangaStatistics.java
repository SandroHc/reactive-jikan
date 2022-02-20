/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import java.io.*;
import java.time.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.sandrohc.jikan.serializer.DaysDeserializer;
import net.sandrohc.jikan.utils.Generated;

/**
 * The manga stats of a MAL user.
 */
public class UserMangaStatistics implements Serializable {

	/** The number of days of manga read. */
	@JsonProperty("days_read")
	@JsonDeserialize(using=DaysDeserializer.class)
	public Duration daysRead;

	/** The mean score. */
	@JsonProperty("mean_score")
	public double meanScore;

	/** The number of manga the user is currently reading. */
	public int reading;

	/** The number of manga the user has read/completed. */
	public int completed;

	/** The number of manga the user has put on-hold. */
	@JsonProperty("on_hold")
	public int onHold;

	/** The number of manga the user has dropped. */
	public int dropped;

	/** The number of manga the user is planning to read. */
	@JsonProperty("plan_to_read")
	public int planToRead;

	/** The total number of manga entries. */
	@JsonProperty("total_entries")
	public int totalEntries;

	/** The number of manga reread. */
	public int reread;

	/** The total number of chapters read. */
	@JsonProperty("chapters_read")
	public int chaptersRead;

	/** The total number of volumes read. */
	@JsonProperty("volumes_read")
	public int volumesRead;


	public Duration getDaysRead() {
		return daysRead;
	}

	public void setDaysRead(Duration daysRead) {
		this.daysRead = daysRead;
	}

	public double getMeanScore() {
		return meanScore;
	}

	public void setMeanScore(double meanScore) {
		this.meanScore = meanScore;
	}

	public int getReading() {
		return reading;
	}

	public void setReading(int reading) {
		this.reading = reading;
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

	public int getPlanToRead() {
		return planToRead;
	}

	public void setPlanToRead(int planToRead) {
		this.planToRead = planToRead;
	}

	public int getTotalEntries() {
		return totalEntries;
	}

	public void setTotalEntries(int totalEntries) {
		this.totalEntries = totalEntries;
	}

	public int getReread() {
		return reread;
	}

	public void setReread(int reread) {
		this.reread = reread;
	}

	public int getChaptersRead() {
		return chaptersRead;
	}

	public void setChaptersRead(int chaptersRead) {
		this.chaptersRead = chaptersRead;
	}

	public int getVolumesRead() {
		return volumesRead;
	}

	public void setVolumesRead(int volumesRead) {
		this.volumesRead = volumesRead;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserMangaStatistics that = (UserMangaStatistics) o;

		if (Double.compare(that.meanScore, meanScore) != 0) return false;
		if (reading != that.reading) return false;
		if (completed != that.completed) return false;
		if (onHold != that.onHold) return false;
		if (dropped != that.dropped) return false;
		if (planToRead != that.planToRead) return false;
		if (totalEntries != that.totalEntries) return false;
		if (reread != that.reread) return false;
		if (chaptersRead != that.chaptersRead) return false;
		if (volumesRead != that.volumesRead) return false;
		return daysRead != null ? daysRead.equals(that.daysRead) : that.daysRead == null;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		long temp;
		result = daysRead != null ? daysRead.hashCode() : 0;
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
		result = 31 * result + volumesRead;
		return result;
	}

	@Override
	public String toString() {
		return "UserMangaStatistics[" +
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
