/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.CacheEntity;

public class AnimeStats extends CacheEntity {

	public int watching;
	public int completed;

	@JsonProperty("on_hold")
	public int onHold;
	public int dropped;

	@JsonProperty("plan_to_watch")
	public int planToWatch;

	public int total;

	public Map<Integer, AnimeStatsScore> scores;


	@Override
	public String toString() {
		return "AnimeStats[" +
			   "total=" + total +
			   ']';
	}

}
