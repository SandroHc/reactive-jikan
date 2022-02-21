/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.utils.Generated;

public class Trailer implements Serializable {

	/** The YouTube ID. */
	@JsonProperty("youtube_id")
	public String youtubeId;

	/** The YouTube URL. */
	public String url;

	/** The YouTube embed URL. */
	@JsonProperty("embed_url")
	public String embedUrl;

	/** The trailer image. */
	public Image images;


	public String getYoutubeId() {
		return youtubeId;
	}

	public void setYoutubeId(String youtubeId) {
		this.youtubeId = youtubeId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEmbedUrl() {
		return embedUrl;
	}

	public void setEmbedUrl(String embedUrl) {
		this.embedUrl = embedUrl;
	}

	public Image getImages() {
		return images;
	}

	public void setImages(Image images) {
		this.images = images;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Trailer that = (Trailer) o;

		return youtubeId != null ? youtubeId.equals(that.youtubeId) : that.youtubeId == null;
	}

	@Generated
	@Override
	public int hashCode() {
		return youtubeId != null ? youtubeId.hashCode() : 0;
	}

	@Generated
	@Override
	public String toString() {
		return "AnimeTrailer[" +
				"youtubeId='" + youtubeId + '\'' +
				", url='" + url + '\'' +
				", embedUrl='" + embedUrl + '\'' +
				", images=" + images +
				']';
	}
}
