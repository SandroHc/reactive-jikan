/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.user;

import java.time.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.legacy.enums.*;

/**
 * The anime details of a MAL user.
 */
public class UserAnime extends MalEntity {

	@JsonProperty("mal_id")
	public int malId;

	public String title;

	public String url;

	@JsonProperty("video_url")
	public String videoUrl;

	@JsonProperty("image_url")
	public String image_url;

	public AnimeType type;

	@JsonProperty("watching_status")
	public UserAnimeWatchingStatus watchingStatus;

	public int score;

	@JsonProperty("watched_episodes")
	public int watchedEpisodes;

	@JsonProperty("total_episodes")
	public int totalEpisodes;

	@JsonProperty("airing_status")
	public UserAnimeAiringStatus airingStatus;

	@JsonProperty("season_name")
	public String seasonName;

	@JsonProperty("seasonYear")
	public Integer seasonYear;

	@JsonProperty("has_episode_video")
	public boolean hasEpisodeVideo;

	@JsonProperty("has_promo_video")
	public boolean hasPromoVideo;

	@JsonProperty("has_video")
	public boolean hasVideo;

	@JsonProperty("is_rewatching")
	public boolean isRewatching;

	public String tags;

	public AgeRating rating;

	@JsonProperty("start_date")
	public OffsetDateTime startDate;

	@JsonProperty("end_date")
	public OffsetDateTime endDate;

	@JsonProperty("watch_start_date")
	public OffsetDateTime watchStartDate;

	@JsonProperty("watch_end_date")
	public OffsetDateTime watchEndDate;

	public int days;

	public UserAnimeStorage storage;

	public UserPriority priority;

	public boolean addedToList;

	public List<String> studios;

	public List<String> licensors;


	@Override
	public String toString() {
		return "UserAnime[" +
				"id=" + malId +
				", title='" + title + '\'' +
				", watchingStatus=" + watchingStatus +
				", score=" + score +
				']';
	}
}
