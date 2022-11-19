/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.manga;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;

/**
 * A manga object with all its details.
 */
public class Manga extends MalEntity {

	/** The URL to the manga on MyAnimeList. */
	public String url;

	/** The banner image. */
	public Images images;

	/** The official manga title. */
	public String title;

	/** The official title, in english. */
	@JsonProperty("title_english")
	public String titleEnglish;

	/** The official title, in japanese. */
	@JsonProperty("title_japanese")
	public String titleJapanese;

	/** The title synonyms. */
	@JsonProperty("title_synonyms")
	public List<String> titleSynonyms;

	/** The publication type. */
	public MangaType type;

	/** The number of chapters. */
	public int chapters;

	/** The number of volumes. */
	public int volumes;

	/** If the manga is still being published. */
	public boolean publishing;

	public DateRange published = new DateRange();

	/** The manga score, in the range of 0.00 to 10.00. */
	@JsonProperty("scored")
	public double score;

	/** The number of users that gave a score to this manga. */
	@JsonProperty("scored_by")
	public int scoredBy;

	/** The manga's rank on MyAnimeList's scoreboards. */
	public int rank;

	/** The manga's popularity rank on MyAnimeList's scoreboards. */
	public int popularity;

	/** The number of users that added this manga to their list. */
	public int members;

	/** The number of users that added this manga to their favorites. */
	public int favorites;

	/** The manga synopsis. */
	public String synopsis;

	/** The background information. */
	public String background;

	/** The authors. */
	public List<EntityWithType> authors = Collections.emptyList();

	/** The serializations. */
	public List<EntityWithType> serializations = Collections.emptyList();

	/** The genres. */
	public List<GenreEntity> genres = Collections.emptyList();

	/** The explicit genres. */
	@JsonAlias("explicit_genres")
	public List<GenreEntity> explicitGenres = Collections.emptyList();

	/** The themes. */
	public List<EntityWithType> themes = Collections.emptyList();

	/** The demographics. */
	public List<EntityWithType> demographics = Collections.emptyList();


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleEnglish() {
		return titleEnglish;
	}

	public void setTitleEnglish(String titleEnglish) {
		this.titleEnglish = titleEnglish;
	}

	public String getTitleJapanese() {
		return titleJapanese;
	}

	public void setTitleJapanese(String titleJapanese) {
		this.titleJapanese = titleJapanese;
	}

	public List<String> getTitleSynonyms() {
		return titleSynonyms;
	}

	public void setTitleSynonyms(List<String> titleSynonyms) {
		this.titleSynonyms = titleSynonyms;
	}

	public MangaType getType() {
		return type;
	}

	public void setType(MangaType type) {
		this.type = type;
	}

	public int getChapters() {
		return chapters;
	}

	public void setChapters(int chapters) {
		this.chapters = chapters;
	}

	public int getVolumes() {
		return volumes;
	}

	public void setVolumes(int volumes) {
		this.volumes = volumes;
	}

	public boolean isPublishing() {
		return publishing;
	}

	public void setPublishing(boolean publishing) {
		this.publishing = publishing;
	}

	public DateRange getPublished() {
		return published;
	}

	public void setPublished(DateRange published) {
		this.published = published;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getScoredBy() {
		return scoredBy;
	}

	public void setScoredBy(int scoredBy) {
		this.scoredBy = scoredBy;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public int getMembers() {
		return members;
	}

	public void setMembers(int members) {
		this.members = members;
	}

	public int getFavorites() {
		return favorites;
	}

	public void setFavorites(int favorites) {
		this.favorites = favorites;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public List<EntityWithType> getAuthors() {
		return authors;
	}

	public void setAuthors(List<EntityWithType> authors) {
		this.authors = authors;
	}

	public List<EntityWithType> getSerializations() {
		return serializations;
	}

	public void setSerializations(List<EntityWithType> serializations) {
		this.serializations = serializations;
	}

	public List<GenreEntity> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreEntity> genres) {
		this.genres = genres;
	}

	public List<GenreEntity> getExplicitGenres() {
		return explicitGenres;
	}

	public void setExplicitGenres(List<GenreEntity> explicitGenres) {
		this.explicitGenres = explicitGenres;
	}

	public List<EntityWithType> getThemes() {
		return themes;
	}

	public void setThemes(List<EntityWithType> themes) {
		this.themes = themes;
	}

	public List<EntityWithType> getDemographics() {
		return demographics;
	}

	public void setDemographics(List<EntityWithType> demographics) {
		this.demographics = demographics;
	}

	@Override
	public String toString() {
		return "Manga[id=" + malId + ", title='" + title + "']";
	}
}
