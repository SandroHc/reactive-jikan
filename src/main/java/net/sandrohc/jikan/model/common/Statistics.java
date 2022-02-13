/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.utils.Generated;

/**
 * The anime/manga statistics.
 */
public class Statistics implements Serializable {

	/** The number of users watching/reading it. */
	@JsonAlias({ "watching", "reading" })
	public int seeing;

	/** The number of users that completed it. */
	public int completed;

	/** The number of users that put it on hold. */
	@JsonProperty("on_hold")
	public int onHold;

	/** The number of users that dropped it. */
	public int dropped;

	/** The number of users that plan on watching it. */
	@JsonProperty("plan_to_see")
	@JsonAlias({ "plan_to_watch", "plan_to_read" })
	public int planToSee;

	/** The total number of users. */
	public int total;

	/** The distribution of the scores given. */
	public Collection<StatisticsScore> scores = Collections.emptyList();


	public int getSeeing() {
		return seeing;
	}

	public void setSeeing(int seeing) {
		this.seeing = seeing;
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

	public int getPlanToSee() {
		return planToSee;
	}

	public void setPlanToSee(int planToSee) {
		this.planToSee = planToSee;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Collection<StatisticsScore> getScores() {
		return scores;
	}

	public void setScores(Collection<StatisticsScore> scores) {
		this.scores = scores;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Statistics that = (Statistics) o;

		if (seeing != that.seeing) return false;
		if (completed != that.completed) return false;
		if (onHold != that.onHold) return false;
		if (dropped != that.dropped) return false;
		if (planToSee != that.planToSee) return false;
		if (total != that.total) return false;
		return scores != null ? scores.equals(that.scores) : that.scores == null;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = seeing;
		result = 31 * result + completed;
		result = 31 * result + onHold;
		result = 31 * result + dropped;
		result = 31 * result + planToSee;
		result = 31 * result + total;
		result = 31 * result + (scores != null ? scores.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "Statistics[total=" + total + ']';
	}
}
