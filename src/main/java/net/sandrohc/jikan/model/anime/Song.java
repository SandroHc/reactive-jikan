/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.io.*;
import java.util.*;

import net.sandrohc.jikan.utils.Generated;

/**
 * An anime song.
 * <p>
 * Information gets extracted from a string. For example:
 */
public class Song implements Serializable {

	/** The number of the song. */
	public int number;

	/** The name of the song. */
	public String name;

	/** The artist of the song. */
	public String artist;

	/** The episodes this song makes in appearance in. */
	public Collection<Integer> episodes = Collections.emptyList();


	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Collection<Integer> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(Collection<Integer> episodes) {
		this.episodes = episodes;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Song song = (Song) o;

		if (number != song.number) return false;
		if (name != null ? !name.equals(song.name) : song.name != null) return false;
		if (artist != null ? !artist.equals(song.artist) : song.artist != null) return false;
		return episodes != null ? episodes.equals(song.episodes) : song.episodes == null;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = number;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (artist != null ? artist.hashCode() : 0);
		result = 31 * result + (episodes != null ? episodes.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "Song[number=" + number + ", name='" + name + '\'' + ", artist='" + artist + '\'' + ", episodes=" + episodes + ']';
	}
}
