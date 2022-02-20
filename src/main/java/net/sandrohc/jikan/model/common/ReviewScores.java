/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;

import net.sandrohc.jikan.utils.Generated;

/**
 * The scores given by a reviewer.
 */
public class ReviewScores implements Serializable {

	/** The overall score. */
	public int overall;

	/** The score for the story. */
	public int story;

	/** The score for the art. Exclusive to manga. */
	public int art;

	/** The score for the animation. Exclusive to anime. */
	public int animation;

	/** The score for the sound. Exclusive to anime. */
	public int sound;

	/** The score for the character development. */
	public int character;

	/** The score for the enjoyment. */
	public int enjoyment;


	public int getOverall() {
		return overall;
	}

	public void setOverall(int overall) {
		this.overall = overall;
	}

	public int getStory() {
		return story;
	}

	public void setStory(int story) {
		this.story = story;
	}

	public int getArt() {
		return art;
	}

	public void setArt(int art) {
		this.art = art;
	}

	public int getAnimation() {
		return animation;
	}

	public void setAnimation(int animation) {
		this.animation = animation;
	}

	public int getSound() {
		return sound;
	}

	public void setSound(int sound) {
		this.sound = sound;
	}

	public int getCharacter() {
		return character;
	}

	public void setCharacter(int character) {
		this.character = character;
	}

	public int getEnjoyment() {
		return enjoyment;
	}

	public void setEnjoyment(int enjoyment) {
		this.enjoyment = enjoyment;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ReviewScores that = (ReviewScores) o;

		if (overall != that.overall) return false;
		if (story != that.story) return false;
		if (art != that.art) return false;
		if (animation != that.animation) return false;
		if (sound != that.sound) return false;
		if (character != that.character) return false;
		return enjoyment == that.enjoyment;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = overall;
		result = 31 * result + story;
		result = 31 * result + art;
		result = 31 * result + animation;
		result = 31 * result + sound;
		result = 31 * result + character;
		result = 31 * result + enjoyment;
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "ReviewerScores[overall=" + overall + ", story=" + story + ", art=" + art + ", animation=" + animation +
				", sound=" + sound + ", character=" + character + ", enjoyment=" + enjoyment + ']';
	}
}
