/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.io.*;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnimeEpisode implements Serializable {

	@JsonProperty("episode_id")
	public int episodeId;

	public String title;

	@JsonProperty("title_japanese")
	public String titleJapanese;

	@JsonProperty("title_romanji")
	public String titleRomanji;

	public OffsetDateTime aired;

	public boolean filler;

	public boolean recap;

	@JsonProperty("video_url")
	public String videoUrl;

	@JsonProperty("forum_url")
	public String forumUrl;


	@Override
	public String toString() {
		return "AnimeEpisode[id=" + episodeId + ", title='" + title + "']";
	}

}
