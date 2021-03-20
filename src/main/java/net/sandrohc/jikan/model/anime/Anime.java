/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.*;

/**
 * An anime object with all its details.
 */
public class Anime extends AnimeBase {

	/** The official trailer URL. */
	@JsonProperty("trailer_url")
	public String trailerUrl;

	/** The official title in english. */
	@JsonProperty("title_english")
	public String titleEnglish;

	/** The official title in japanese. */
	@JsonProperty("title_japanese")
	public String titleJapanese;

	/** The title synonyms. */
	@JsonProperty("title_synonyms")
	public List<String> titleSynonyms;

	/** The source material. */
	public String source;

	/** The current status. */
	public AnimeStatus status;

	/** The average runtime duration of the episodes. */
	public String duration;

	/** The number of users that gave a score to this anime. */
	@JsonProperty("scored_by")
	public int scoredBy;

	/** The anime's rank on MyAnimeList's scoreboards. */
	public int rank;

	/** The anime's popularity on MyAnimeList's scoreboards. */
	public int popularity;

	/** The number of users that added this anime to their favorites. */
	public int favorites;

	/** The background information. */
	public String background;

	/** The season the anime was premiered in. e.g. "Summer 2010" */
	public String premiered;

	/** The broadcast times for when the anime as airing. e.g. "Sundays at 00:00 (JST)" */
	public String broadcast;

	/** A list of related material. */
	public Related related;

	/** The producers. */
	public List<MalSubEntity> producers;

	/** The licensors. */
	public List<MalSubEntity> licensors;

	/** The studios. */
	public List<MalSubEntity> studios;

	/** The genres. */
	public List<GenreEntity<AnimeGenre>> genres;

	/** The names of the opening theme songs. */
	@JsonProperty("opening_themes")
	public List<String> openingThemes;

	/** The names of the ending theme songs. */
	@JsonProperty("ending_themes")
	public List<String> endingThemes;

}
