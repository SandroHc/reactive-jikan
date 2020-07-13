/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.CacheEntity;
import net.sandrohc.jikan.model.search.AnimeSearchSub;

public class AnimeEpisodes extends CacheEntity {

	public List<AnimeEpisodesSub> episodes;

	@JsonProperty("episodes_last_page")
	public int lastPage;


	@Override
	public String toString() {
		return "AnimeEpisodes[" + // TODO
			   ']';
	}

}
