/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.util.List;

import net.sandrohc.jikan.model.base.CacheEntity;

public class AnimeVideos extends CacheEntity {

	public List<AnimeVideosPromo> promo;

	public List<AnimeVideosEpisode> episodes;


	@Override
	public String toString() {
		return "AnimeVideos[" + // TODO
			   ']';
	}

}
