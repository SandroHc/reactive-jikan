/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;

import net.sandrohc.jikan.utils.Generated;

/**
 * The information about a given score, like number of votes and percentage of total votes.
 */
public class StatisticsScore implements Serializable {

	/** The score, a value from 1 to 10. */
	public int score;

	/** The total votes for this score. */
	public int votes;

	/** The percentage of total votes that are in this score. */
	public double percentage;


	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		StatisticsScore that = (StatisticsScore) o;

		if (score != that.score) return false;
		if (votes != that.votes) return false;
		return Double.compare(that.percentage, percentage) == 0;
	}

	@Generated
	@Override
	public int hashCode() {
		int result;
		long temp;
		result = score;
		result = 31 * result + votes;
		temp = Double.doubleToLongBits(percentage);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "StatisticsScore[score=" + score + ", votes=" + votes + ", percentage=" + percentage + ']';
	}
}
