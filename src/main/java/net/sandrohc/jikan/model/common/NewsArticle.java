/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewsArticle {

	public String url;

	public String title;

	public OffsetDateTime date;

	@JsonProperty("author_name")
	public String authorName;

	@JsonProperty("author_url")
	public String authorUrl;

	@JsonProperty("forum_url")
	public String forumUrl;

	@JsonProperty("image_url")
	public String imageUrl;

	public int comments;

	public String intro;


	@Override
	public String toString() {
		return "NewsArticle[url='" + url + "', title='" + title + "']";
	}

}
