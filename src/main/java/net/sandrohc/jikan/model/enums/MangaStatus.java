/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum MangaStatus {
	@JsonProperty("all")        ALL,
	@JsonProperty("publishing") PUBLISHING,
	@JsonProperty("completed")
	@JsonAlias("complete")      COMPLETED,
	@JsonProperty("to_be_published")
	@JsonAlias({ "tbp", "upcoming" }) TO_BE_PUBLISHED,
}
