/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.CacheEntity;

/**
 * The base object for the anime/manga statistics query.
 */
public class Stats extends CacheEntity {

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
	public Map<Integer, StatsScore> scores;


	@Override
	public String toString() {
		return "Stats[total=" + total + ']';
	}

}
