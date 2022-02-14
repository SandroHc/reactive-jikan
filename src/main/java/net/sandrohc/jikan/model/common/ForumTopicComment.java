/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;
import java.time.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.utils.Generated;

public class ForumTopicComment implements Serializable {

	/** The URL to the forum topic comment on MyAnimeList. */
	public String url;

	/** The author name. */
	@JsonProperty("author_username")
	public String authorUsername;

	/** The URL to the author's profile on MyAnimeList. */
	@JsonProperty("author_url")
	public String authorUrl;

	/** The date the forum topic comment was posted. */
	public OffsetDateTime date;


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthorUsername() {
		return authorUsername;
	}

	public void setAuthorUsername(String authorUsername) {
		this.authorUsername = authorUsername;
	}

	public String getAuthorUrl() {
		return authorUrl;
	}

	public void setAuthorUrl(String authorUrl) {
		this.authorUrl = authorUrl;
	}

	public OffsetDateTime getDate() {
		return date;
	}

	public void setDate(OffsetDateTime date) {
		this.date = date;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ForumTopicComment that = (ForumTopicComment) o;

		if (url != null ? !url.equals(that.url) : that.url != null) return false;
		if (authorUsername != null ? !authorUsername.equals(that.authorUsername) : that.authorUsername != null)
			return false;
		if (authorUrl != null ? !authorUrl.equals(that.authorUrl) : that.authorUrl != null) return false;
		return date != null ? date.equals(that.date) : that.date == null;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = url != null ? url.hashCode() : 0;
		result = 31 * result + (authorUsername != null ? authorUsername.hashCode() : 0);
		result = 31 * result + (authorUrl != null ? authorUrl.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "ForumTopicComment[url='" + url + "', authorUsername='" + authorUsername + "']";
	}
}
