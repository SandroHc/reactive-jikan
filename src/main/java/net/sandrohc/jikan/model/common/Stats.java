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

public class Stats extends CacheEntity {

	@JsonAlias({ "watching", "reading" })
	public int seeing;

	public int completed;

	@JsonProperty("on_hold")
	public int onHold;

	public int dropped;

	@JsonProperty("plan_to_see")
	@JsonAlias({ "plan_to_watch", "plan_to_read" })
	public int planToSee;

	public int total;

	public Map<Integer, StatsScore> scores;


	@Override
	public String toString() {
		return "Stats[total=" + total + ']';
	}

}
