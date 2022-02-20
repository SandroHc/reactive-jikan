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
 * An anime/manga news article.
 */
public class NewsArticle extends MalEntity {

	/** The URL for the news article page on MyAnimeList. */
	public String url;

	/** The news article title. */
	public String title;

	/** The news article publishing date. */
	public OffsetDateTime date;

	/** The author name. */
	@JsonProperty("author_username")
	public String authorUsername;

	/** The URL to the author's profile on MyAnimeList. */
	@JsonProperty("author_url")
	public String authorUrl;

	/** The news article forum URL. */
	@JsonProperty("forum_url")
	public String forumUrl;

	/** The news article image. */
	public Images images;

	/** The number of comments in this news article. */
	public int comments;

	/** A summary of the article text. Never exceeds 170 characters. */
	public String excerpt;


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

	public String getForumUrl() {
		return forumUrl;
	}

	public void setForumUrl(String forumUrl) {
		this.forumUrl = forumUrl;
	}

	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public String getExcerpt() {
		return excerpt;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}

	@Generated
	@Override
	public String toString() {
		return "NewsArticle[id=" + malId + ", title='" + title + "', url='" + url + "']";
	}

}
