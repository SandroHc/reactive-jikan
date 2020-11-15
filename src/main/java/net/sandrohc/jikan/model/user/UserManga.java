/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import java.time.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.enums.*;

/**
 * The manga details of a MAL user.
 */
public class UserManga extends MalEntity {

	@JsonProperty("mal_id")
	public int malId;

	public String title;

	public String url;

	@JsonProperty("image_url")
	public String image_url;

	public MangaType type;

	@JsonProperty("reading_status")
	public UserMangaReadingStatus readingStatus;

	public int score;

	@JsonProperty("read_chapters")
	public int readChapters;

	@JsonProperty("read_volumes")
	public int readVolumes;

	@JsonProperty("total_chapters")
	public int totalChapters;

	@JsonProperty("total_volumes")
	public int totalVolumes;

	@JsonProperty("publishing_status")
	public UserMangaPublishingStatus publishingStatus;

	@JsonProperty("is_rereading")
	public boolean isRereading;

	public String tags;

	@JsonProperty("start_date")
	public OffsetDateTime startDate;

	@JsonProperty("end_date")
	public OffsetDateTime endDate;

	@JsonProperty("read_start_date")
	public OffsetDateTime readStartDate;

	@JsonProperty("read_end_date")
	public OffsetDateTime readEndDate;

	public int days;

	public String retail;

	public UserPriority priority;

	public boolean addedToList;

	public List<Object> magazines;


	@Override
	public String toString() {
		return "UserManga[" +
				"id=" + malId +
				", title='" + title + '\'' +
				", readingStatus=" + readingStatus +
				", score=" + score +
				']';
	}
}
