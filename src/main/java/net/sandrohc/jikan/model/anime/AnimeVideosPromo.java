/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnimeVideosPromo {

	public String title;

	@JsonProperty("image_url")
	public String imageUrl;

	@JsonProperty("image_url")
	public String videoUrl;


	@Override
	public String toString() {
		return "AnimeVideosPromo[" +
			   "title='" + title + '\'' +
			   ']';
	}

}
