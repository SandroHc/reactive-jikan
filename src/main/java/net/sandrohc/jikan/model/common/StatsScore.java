/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;

/**
 * The information about a given score, like number of votes and percentage of total votes.
 */
public class StatsScore implements Serializable {

	/** The total votes for this score */
	public int votes;

	/** The percentage of total votes are in this score */
	public float percentage; // TODO: normalize to fit inside [0,1]?


	@Override
	public String toString() {
		return "StatsScore[votes=" + votes + ", percentage=" + percentage + ']';
	}

}
