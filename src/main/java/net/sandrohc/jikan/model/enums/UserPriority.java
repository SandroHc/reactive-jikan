/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserPriority {
	@JsonEnumDefaultValue   UNKNOWN,
	@JsonProperty("Low")    LOW,
	@JsonProperty("Medium") MEDIUM,
	@JsonProperty("High")   HIGH,
}
