/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.watch;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;

/**
 * A list of recent episodes for a given anime.
 */
public class WatchEpisodes implements Serializable {

	/** The anime entry. */
	public EntityWithImage entry;

	/** The recent episodes. */
	public Collection<WatchEpisode> episodes = Collections.emptyList();

	/** Whether it is region locked. */
	@JsonProperty("region_locked")
	public boolean regionLocked;


	public EntityWithImage getEntry() {
		return entry;
	}

	public void setEntry(EntityWithImage entry) {
		this.entry = entry;
	}

	public Collection<WatchEpisode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(Collection<WatchEpisode> episodes) {
		this.episodes = episodes;
	}

	public boolean isRegionLocked() {
		return regionLocked;
	}

	public void setRegionLocked(boolean regionLocked) {
		this.regionLocked = regionLocked;
	}

	@Override
	public String toString() {
		return "WatchEpisodes[entry=" + entry + ", episodes=" + episodes + ", regionLocked=" + regionLocked + ']';
	}
}
