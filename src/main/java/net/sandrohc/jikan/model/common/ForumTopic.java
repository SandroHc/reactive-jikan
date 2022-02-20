/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.time.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * A forum post/topic.
 */
public class ForumTopic extends MalEntity {

	/** The URL to the forum topic on MyAnimeList. */
	public String url;

	/** The forum topic title. */
	public String title;

	/** The date the forum topic was posted. */
	public OffsetDateTime date;

	/** The author name. */
	@JsonProperty("author_username")
	public String authorUsername;

	/** The URL to the author's profile on MyAnimeList. */
	@JsonProperty("author_url")
	public String authorUrl;

	/** The number of comments on this forum post/topic. */
	public int comments;

	/** The last comment on this forum post/topic. */
	@JsonProperty("last_comment")
	public ForumTopicComment lastComment;


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public OffsetDateTime getDate() {
		return date;
	}

	public void setDate(OffsetDateTime date) {
		this.date = date;
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

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public ForumTopicComment getLastComment() {
		return lastComment;
	}

	public void setLastComment(ForumTopicComment lastComment) {
		this.lastComment = lastComment;
	}

	@Generated
	@Override
	public String toString() {
		return "ForumTopic[id=" + malId + ", title='" + title + "', url='" + url + "']";
	}

}
