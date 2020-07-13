/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.Genre;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.enums.*;

/**
 * An anime object with all its details.
 */
public class Anime extends MalEntity {

	public String url;

	@JsonProperty("image_url")
	public String imageUrl;

	@JsonProperty("trailer_url")
	public String trailerUrl;

	public String title;

	@JsonProperty("title_english")
	public String titleEnglish;

	@JsonProperty("title_japanese")
	public String titleJapanese;

	@JsonProperty("title_synonyms")
	public List<String> titleSynonyms;

	public AnimeType type;

	public String source;

	public int episodes;

	public AnimeStatus status;

	public Aired aired;

	public String duration;

	public AgeRating rating;

	public float score;

	@JsonProperty("scored_by")
	public int scoredBy;

	public int rank;

	public int popularity;

	public int members;

	public int favorites;

	public String synopsis;

	public Object background; // TODO

	public String premiered;

	public String broadcast;

	public RelatedAnime related;

	public List<Object> producers; // TODO

	public List<Licensor> licensors;

	public List<Studio> studios;

	public List<Genre> genres;

	@JsonProperty("opening_themes")
	public List<String> openingThemes;

	@JsonProperty("ending_themes")
	public List<String> endingThemes;



	@Override
	public String toString() {
		return "Anime[" +
			   "id=" + malId +
			   ", title='" + title + '\'' +
			   ']';
	}

}
