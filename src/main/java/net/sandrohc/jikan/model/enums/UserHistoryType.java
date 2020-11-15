/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserHistoryType {
	@JsonEnumDefaultValue  UNKNOWN,
	@JsonProperty("anime") ANIME,
	@JsonProperty("manga") MANGA,
}
