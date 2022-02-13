/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum RelatedType {
	@JsonProperty("Prequel")      PREQUEL,
	@JsonProperty("Alternative version") ALTERNATIVE_VERSION,
	@JsonProperty("Alternative setting") ALTERNATIVE_SETTING,
	@JsonProperty("Character")    CHARACTER,
	@JsonProperty("Spin-off")     SPIN_OFF,
	@JsonProperty("Adaptation")   ADAPTATION,
	@JsonProperty("Summary")      SUMMARY,
	@JsonProperty("Sequel")       SEQUEL,
	@JsonProperty("Side story")   SIDE_STORY,
	@JsonProperty("Other")        OTHER,
	@JsonProperty("Parent story") PARENT_STORY,
	@JsonProperty("Full story")   FULL_STORY,
	@JsonEnumDefaultValue UNKNOWN,
}
