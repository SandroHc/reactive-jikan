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
public class Manga extends MangaBase {

	/** The official title, in english. */
	@JsonProperty("title_english")
	public String titleEnglish;

	/** The official title, in japanese. */
	@JsonProperty("title_japanese")
	public String titleJapanese;

	@JsonProperty("title_synonyms")
	public List<String> titleSynonyms;

	public DateRange published;

	public int rank;

	public float score;

	@JsonProperty("scored_by")
	public int scoredBy;

	public int popularity;

	public int members;

	public int favorites;

	public String background;

	public Related related;

	public List<GenreEntity<MangaGenre>> genres = Collections.emptyList();

	public List<MalSubEntity> authors = Collections.emptyList();

	public List<MalSubEntity> serializations = Collections.emptyList();

}
