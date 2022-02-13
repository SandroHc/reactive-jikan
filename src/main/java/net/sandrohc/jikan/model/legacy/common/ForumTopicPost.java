/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.common;

import java.io.*;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A post on a forum topic.
 */
public class ForumTopicPost implements Serializable {

	/** The URL to the topic post on MyAnimeList. */
	public String url;

	@JsonProperty("author_name")
	public String authorName;

	@JsonProperty("author_url")
	public String authorUrl;

	@JsonProperty("date_posted")
	public OffsetDateTime datePosted;


	@Override
	public String toString() {
		return "ForumTopicPost[url='" + url + "', authorName='" + authorName + "', datePosted=" + datePosted + ']';
	}

}
