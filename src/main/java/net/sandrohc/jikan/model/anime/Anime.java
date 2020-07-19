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

	public String source;

	public AnimeStatus status;

	public String duration;

	@JsonProperty("scored_by")
	public int scoredBy;

	public int rank;

	public int popularity;

	public int favorites;

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

}
