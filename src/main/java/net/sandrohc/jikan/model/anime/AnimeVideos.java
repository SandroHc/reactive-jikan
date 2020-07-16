/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.util.*;

import net.sandrohc.jikan.model.base.CacheEntity;

public class AnimeVideos extends CacheEntity {

	public List<AnimeVideosPromo> promo = Collections.emptyList();

	public List<AnimeVideosEpisode> episodes = Collections.emptyList();


	@Override
	public String toString() {
		return "AnimeVideos[promo=" + promo.size() + " promos, episodes=" + episodes.size() + " episodes]";
	}

}
