/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.time.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.serializer.EpisodeDurationDeserializer;
import net.sandrohc.jikan.utils.Generated;

/**
 * An anime episode.
 */
public class AnimeEpisode extends MalEntity {

	/** The URL to MyAnimeList. */
	public String url;

	/** The official title, in english. */
	public String title;

	/** The official title, in japanese. */
	@JsonProperty("title_japanese")
	public String titleJapanese;

	/** The official title, in romanji. */
	@JsonProperty("title_romanji")
	public String titleRomanji;

	/** The episode duration, in seconds. */
	@JsonDeserialize(using=EpisodeDurationDeserializer.class)
	public Duration duration;

	/** The original airing date */
	public OffsetDateTime aired;

	/** If it's a filler episode. */
	public boolean filler;

	/** If it's a recap episode. */
	public boolean recap;

	/** The URL to MyAnimeList's forum post related to this episode. */
	@JsonProperty("forum_url")
	public String forumUrl;

	/** The episode synopsis. */
	public String synopsis;


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

	public String getTitleJapanese() {
		return titleJapanese;
	}

	public void setTitleJapanese(String titleJapanese) {
		this.titleJapanese = titleJapanese;
	}

	public String getTitleRomanji() {
		return titleRomanji;
	}

	public void setTitleRomanji(String titleRomanji) {
		this.titleRomanji = titleRomanji;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public OffsetDateTime getAired() {
		return aired;
	}

	public void setAired(OffsetDateTime aired) {
		this.aired = aired;
	}

	public boolean isFiller() {
		return filler;
	}

	public void setFiller(boolean filler) {
		this.filler = filler;
	}

	public boolean isRecap() {
		return recap;
	}

	public void setRecap(boolean recap) {
		this.recap = recap;
	}

	public String getForumUrl() {
		return forumUrl;
	}

	public void setForumUrl(String forumUrl) {
		this.forumUrl = forumUrl;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	@Generated
	@Override
	public String toString() {
		return "AnimeEpisode[id=" + malId + ", title='" + title + "']";
	}
}
