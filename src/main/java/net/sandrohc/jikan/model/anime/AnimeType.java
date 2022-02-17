/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type of publication for an anime.
 */
public enum AnimeType {
	@JsonProperty("All")     ALL     (null),
	@JsonProperty("TV")      TV      ("tv"),
	@JsonProperty("OVA")     OVA     ("ova"),
	@JsonProperty("ONA")     ONA     ("ona"),
	@JsonProperty("Movie")   MOVIE   ("movie"),
	@JsonProperty("Special") SPECIAL ("special"),
	@JsonProperty("Music")   MUSIC   ("music"),
	@JsonEnumDefaultValue    UNKNOWN (null);


	/** Used in the search queries. */
	public final String search;

	AnimeType(String search) {
		this.search = search;
	}

	public String getSearch() {
		return search;
	}
}
