/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.season.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * An anime object with all its details.
 */
public class Anime extends MalEntity {

	/** The URL to the anime page on MyAnimeList. */
	public String url;

	/** The banner image. */
	public Images images;

	/** The trailer. */
	public AnimeTrailer trailer;

	/** The official title. */
	public String title;

	/** The official title in english. */
	@JsonProperty("title_english")
	public String titleEnglish;

	/** The official title in japanese. */
	@JsonProperty("title_japanese")
	public String titleJapanese;

	/** The title synonyms. */
	@JsonProperty("title_synonyms")
	public List<String> titleSynonyms;

	/** The publication type. */
	public AnimeType type;

	/** The source material. */
	public String source; // TODO: convert to enum

	/** The number of episodes. Can be {@code null} if it is unknown. */
	public Integer episodes;

	/** The current status. */
	public AnimeStatus status;

	/** If the anime is currently airing. Similar to checking if the status matches 'AIRING'. */
	public boolean airing;

	/** The airing start and end dates. */
	public DateRange aired = new DateRange();

	/** The average runtime duration of the episodes. */
	public String duration; // TODO: convert string to Duration class

	/** The anime age rating. */
	@JsonAlias("rated")
	public AgeRating rating;

	/** The anime score, in the range of 0.00 to 10.00. */
	public double score;

	/** The number of users that gave a score to this anime. */
	@JsonProperty("scored_by")
	public int scoredBy;

	/** The anime's rank on MyAnimeList's scoreboards. */
	public int rank;

	/** The anime's popularity on MyAnimeList's scoreboards. */
	public int popularity;

	/** The number of users that added this anime to their list. */
	public int members;

	/** The number of users that added this anime to their favorites. */
	public int favorites;

	/** The anime synopsis. */
	public String synopsis;

	/** The background information. */
	public String background;

	/** The season the anime was premiered in, e.g. "Summer". Can be {@code null} if it is unknown. */
	public Season season;

	/** The season the anime was premiered in, e.g. "2010". Can be {@code null} if it is unknown. */
	public Integer year;

	/** The broadcast times for when the anime as airing. e.g. "Sundays at 00:00 (JST)" */
	public AnimeBroadcast broadcast;

	/** The producers. */
	public Collection<EntityWithType> producers = Collections.emptyList();

	/** The licensors. */
	public Collection<EntityWithType> licensors = Collections.emptyList();

	/** The studios. */
	public Collection<EntityWithType> studios = Collections.emptyList();

	/** The genres. */
	public Collection<GenreEntity<AnimeGenre>> genres = Collections.emptyList();

	/** The explicit genres. */
	@JsonAlias("explicit_genres")
	public Collection<GenreEntity<AnimeGenre>> explicitGenres = Collections.emptyList();

	/** The themes. */
	public Collection<GenreEntity<AnimeGenre>> themes = Collections.emptyList();

	/** The demographics. */
	public Collection<EntityWithType> demographics = Collections.emptyList(); // TODO: Convert to enum?


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

	public AnimeTrailer getTrailer() {
		return trailer;
	}

	public void setTrailer(AnimeTrailer trailer) {
		this.trailer = trailer;
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

	public AnimeType getType() {
		return type;
	}

	public void setType(AnimeType type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getEpisodes() {
		return episodes;
	}

	public void setEpisodes(Integer episodes) {
		this.episodes = episodes;
	}

	public AnimeStatus getStatus() {
		return status;
	}

	public void setStatus(AnimeStatus status) {
		this.status = status;
	}

	public boolean isAiring() {
		return airing;
	}

	public void setAiring(boolean airing) {
		this.airing = airing;
	}

	public DateRange getAired() {
		return aired;
	}

	public void setAired(DateRange aired) {
		this.aired = aired;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public AgeRating getRating() {
		return rating;
	}

	public void setRating(AgeRating rating) {
		this.rating = rating;
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

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public AnimeBroadcast getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(AnimeBroadcast broadcast) {
		this.broadcast = broadcast;
	}

	public Collection<EntityWithType> getProducers() {
		return producers;
	}

	public void setProducers(Collection<EntityWithType> producers) {
		this.producers = producers;
	}

	public Collection<EntityWithType> getLicensors() {
		return licensors;
	}

	public void setLicensors(Collection<EntityWithType> licensors) {
		this.licensors = licensors;
	}

	public Collection<EntityWithType> getStudios() {
		return studios;
	}

	public void setStudios(Collection<EntityWithType> studios) {
		this.studios = studios;
	}

	public Collection<GenreEntity<AnimeGenre>> getGenres() {
		return genres;
	}

	public void setGenres(Collection<GenreEntity<AnimeGenre>> genres) {
		this.genres = genres;
	}

	public Collection<GenreEntity<AnimeGenre>> getExplicitGenres() {
		return explicitGenres;
	}

	public void setExplicitGenres(Collection<GenreEntity<AnimeGenre>> explicitGenres) {
		this.explicitGenres = explicitGenres;
	}

	public Collection<GenreEntity<AnimeGenre>> getThemes() {
		return themes;
	}

	public void setThemes(Collection<GenreEntity<AnimeGenre>> themes) {
		this.themes = themes;
	}

	public Collection<EntityWithType> getDemographics() {
		return demographics;
	}

	public void setDemographics(Collection<EntityWithType> demographics) {
		this.demographics = demographics;
	}

	@Generated
	@Override
	public String toString() {
		return "Anime[id=" + malId + ", title='" + title + "']";
	}
}
