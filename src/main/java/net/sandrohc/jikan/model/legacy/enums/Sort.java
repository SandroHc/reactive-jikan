/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.enums;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The sort order.
 */
public enum Sort {
	@JsonProperty("ascending")
	@JsonAlias("asc")
	ASCENDING("asc"),

	@JsonProperty("descending")
	@JsonAlias("desc")
	DESCENDING("desc"),
	;

	public final String key;

	Sort(String key) {
		this.key = key;
	}

}
