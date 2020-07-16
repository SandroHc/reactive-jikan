/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.manga;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.*;

/**
 * A manga object with all its details.
 */
public class Manga extends MalEntity {

	public String url;

	public String title;

	@JsonProperty("title_english")
	public String titleEnglish;

	@JsonProperty("title_japanese")
	public String titleJapanese;

	@JsonProperty("title_synonyms")
	public List<String> titleSynonyms;

	public MangaStatus status;

	@JsonProperty("image_url")
	public String imageUrl;

	public MangaType type;

	public int volumes;

	public int chapters;

	public boolean publishing;

	public DateRange published;

	public int rank;

	public float score;

	@JsonProperty("scored_by")
	public int scoredBy;

	public int popularity;

	public int members;

	public int favorites;

	public String synopsis;

	public String background;

	public Related related;

	public List<GenreEntity<MangaGenre>> genres = Collections.emptyList();

	public List<MalSubEntity> authors = Collections.emptyList();

	public List<MalSubEntity> serializations = Collections.emptyList();


	@Override
	public String toString() {
		return "Manga[id=" + malId + ", title='" + title + "']";
	}

}
