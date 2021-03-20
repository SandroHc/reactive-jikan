/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.io.*;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A promotional video for an episode.
 */
public class AnimeVideosEpisode implements Serializable {

	public String title;

	public String episode;

	/** The URL to the anime videos page on MyAnimeList. */
	@JsonProperty("url")
	public String url;

	@JsonProperty("image_url")
	public String imageUrl;


	@Override
	public String toString() {
		return "AnimeVideosEpisode[title='" + title + "', episode='" + title + "']";
	}

}
