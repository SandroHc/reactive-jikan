/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnimeVideosEpisode {

	public String title;

	public String episode;

	@JsonProperty("url")
	public String url;

	@JsonProperty("image_url")
	public String imageUrl;


	@Override
	public String toString() {
		return "AnimeVideosEpisode[title='" + title + "', episode='" + title + "']";
	}

}
