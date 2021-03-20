/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.io.*;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A promotional video.
 */
public class AnimeVideosPromo implements Serializable {

	public String title;

	@JsonProperty("image_url")
	public String imageUrl;

	@JsonProperty("video_url")
	public String videoUrl;


	@Override
	public String toString() {
		return "AnimeVideosPromo[title='" + title + "']";
	}

}
