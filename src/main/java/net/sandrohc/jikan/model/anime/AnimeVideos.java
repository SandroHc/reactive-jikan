/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.io.*;
import java.util.*;

import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * The anime videos.
 */
public class AnimeVideos implements Serializable {

	/** The promotional videos. */
	public List<Promo> promo = Collections.emptyList();

	/** The episode videos. */
	public List<AnimeVideosEpisode> episodes = Collections.emptyList();


	public List<Promo> getPromo() {
		return promo;
	}

	public void setPromo(List<Promo> promo) {
		this.promo = promo;
	}

	public List<AnimeVideosEpisode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<AnimeVideosEpisode> episodes) {
		this.episodes = episodes;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AnimeVideos that = (AnimeVideos) o;

		if (promo != null ? !promo.equals(that.promo) : that.promo != null) return false;
		return episodes != null ? episodes.equals(that.episodes) : that.episodes == null;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = promo != null ? promo.hashCode() : 0;
		result = 31 * result + (episodes != null ? episodes.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "AnimeVideos[promo=" + promo.size() + " promos, episodes=" + episodes.size() + " episodes]";
	}
}
