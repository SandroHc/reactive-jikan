/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Storage used by the user for a anime on his list.
 */
public enum UserAnimeStorage {
	@JsonEnumDefaultValue        UNKNOWN,
	@JsonProperty("Hard Drive")  HARD_DRIVE,
	@JsonProperty("External HD") EXTERNAL_HD,
	@JsonProperty("NAS")         NAS,
	@JsonProperty("Blu-ray")     BLURAY,
	@JsonProperty("DVD / CD")    DVD,
	@JsonProperty("Retail DVD")  DVD_RETAIL,
	@JsonProperty("VHS")         VHS,
	@JsonProperty("None")        NONE,
}
