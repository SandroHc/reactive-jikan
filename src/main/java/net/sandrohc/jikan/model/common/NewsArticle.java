/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An anime/manga news article.
 */
public class NewsArticle implements Serializable {

	/** The URL for the article page on MyAnimeList. */
	public String url;

	/** The article title. */
	public String title;

	/** The article publishing date. */
	public OffsetDateTime date;

	/** The author's name. */
	@JsonProperty("author_name")
	public String authorName;

	/** The URL to the author's profile on MyAnimeList. */
	@JsonProperty("author_url")
	public String authorUrl;

	/** The article forum URL. */
	@JsonProperty("forum_url")
	public String forumUrl;

	/** The article image. */
	@JsonProperty("image_url")
	public String imageUrl;

	/** The number of comments in this article. */
	public int comments;

	/** A summary of the article text. Never exceeds 170 characters. */
	public String intro;


	@Override
	public String toString() {
		return "NewsArticle[url='" + url + "', title='" + title + "']";
	}

}
