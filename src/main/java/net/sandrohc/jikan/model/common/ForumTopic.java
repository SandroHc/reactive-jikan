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
 * A forum topic.
 */
public class ForumTopic implements Serializable {

	@JsonProperty("topic_id")
	public int topicId;

	/** The URL to the forum topic on MyAnimeList. */
	public String url;

	/** The topic title. */
	public String title;

	@JsonProperty("date_posted")
	public OffsetDateTime datePosted;

	@JsonProperty("author_name")
	public String authorName;

	@JsonProperty("author_url")
	public String authorUrl;

	public int replies;

	@JsonProperty("last_post")
	public ForumTopicPost lastPost;


	@Override
	public String toString() {
		return "ForumTopic[id=" + topicId + ", title='" + title + "']";
	}

}
