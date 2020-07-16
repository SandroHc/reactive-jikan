/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MangaType {
	@JsonProperty("All")      ALL,
	@JsonProperty("Manga")    MANGA,
	@JsonProperty("Novel")    NOVEL,
	@JsonProperty("One-shot") ONESHOT,
	@JsonProperty("Doujin")   DOUJIN,
	@JsonProperty("Manhwa")   MANWHA, // Korean comics
	@JsonProperty("Manhua")   MANUHA, // Chinese comics
}
