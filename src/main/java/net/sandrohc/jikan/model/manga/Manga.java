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

	/** The manga's rank on MyAnimeList's scoreboards. */
	public int rank;

	/** The number of users that gave a score to this manga. */
	@JsonProperty("scored_by")
	public int scoredBy;

	/** The manga's popularity rank on MyAnimeList's scoreboards. */
	public int popularity;

	/** The number of users that added this anime to their favorites. */
	public int favorites;

	/** The background information. */
	public String background;

	/** A list of related material. */
	public Related related;

	/** The genres. */
	public List<GenreEntity<MangaGenre>> genres = Collections.emptyList();

	/** The authors. */
	public List<MalSubEntity> authors = Collections.emptyList();

	/** The serializations. */
	public List<MalSubEntity> serializations = Collections.emptyList();

}
