/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnimeStatsScore {

	public int votes;

	public float percentage;


	@Override
	public String toString() {
		return "AnimeStatsScore[" +
			   "votes=" + votes +
			   ", percentage=" + percentage +
			   ']';
	}

}
