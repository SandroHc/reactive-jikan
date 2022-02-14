/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.manga;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type of publication for a manga.
 */
public enum MangaType {
	@JsonProperty("All")         ALL         (null),
	@JsonProperty("Manga")       MANGA       ("manga"),
	@JsonProperty("Novel")       NOVEL       ("novel"),
	@JsonProperty("Light-novel") LIGHT_NOVEL ("lightnovel"),
	@JsonProperty("One-shot")    ONESHOT     ("oneshot"),
	@JsonProperty("Doujin")      DOUJIN      ("doujin"),
	@JsonProperty("Manhwa")      MANHWA      ("manhwa"), // Korean comics
	@JsonProperty("Manhua")      MANUHA      ("manhua"), // Chinese comics
	@JsonEnumDefaultValue        UNKNOWN     (null);


	/** Used in the search queries. */
	public final String search;

	MangaType(String search) {
		this.search = search;
	}

	public String getSearch() {
		return search;
	}
}
