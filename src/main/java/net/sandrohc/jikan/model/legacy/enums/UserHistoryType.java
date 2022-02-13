/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type of history.
 */
public enum UserHistoryType {
	@JsonEnumDefaultValue  UNKNOWN,
	@JsonProperty("anime") ANIME,
	@JsonProperty("manga") MANGA,
}
