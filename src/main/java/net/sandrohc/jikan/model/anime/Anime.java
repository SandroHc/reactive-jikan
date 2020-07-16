/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.GenreEntity;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.*;

/**
 * An anime object with all its details.
 */
public class Anime extends MalEntity {

	/** The anime page URL on MyAnimeList. */
	public String url;

	/** The banner image URL. */
	@JsonProperty("image_url")
	public String imageUrl;

	/** The official trailer URL. */
	@JsonProperty("trailer_url")
	public String trailerUrl;

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

	public AnimeType type;

	public String source;

	public int episodes;

	public AnimeStatus status;

	public DateRange aired;

	public String duration;

	/** The anime age rating. */
	public AgeRating rating;

	/** The anime score, in the range of 0.00 to 10.00. */
	public float score;

	@JsonProperty("scored_by")
	public int scoredBy;

	public int rank;

	public int popularity;

	public int members;

	public int favorites;

	public String synopsis;

	public String background;

	/** The season the anime was premiered in. e.g. "Summer 2010" */
	public String premiered;

	/** The broadcast times for when the anime as airing. e.g. "Sundays at 00:00 (JST)" */
	public String broadcast;

	public Related related;

	public List<MalSubEntity> producers;

	public List<MalSubEntity> licensors;

	public List<MalSubEntity> studios;

	public List<GenreEntity<AnimeGenre>> genres;

	@JsonProperty("opening_themes")
	public List<String> openingThemes;

	@JsonProperty("ending_themes")
	public List<String> endingThemes;


	@Override
	public String toString() {
		return "Anime[id=" + malId + ", title='" + title + "']";
	}

}
