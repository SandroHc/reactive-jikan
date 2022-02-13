/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;

/**
 * A video for an episode.
 */
public class AnimeVideosEpisode extends MalEntity {

	/** The video title. */
	public String title;

	/** The anime episode title. */
	public String episode;

	/** The URL to the anime episode on MyAnimeList. */
	@JsonProperty("url")
	public String url;

	/** The video images. */
	public Images images;


	@Override
	public String toString() {
		return "AnimeVideosEpisode[id=" + malId + ", title='" + title + "', episode='" + episode + "']";
	}
}
