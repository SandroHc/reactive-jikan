/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The user's gender.
 */
public enum UserGender {
	@JsonEnumDefaultValue   UNKNOWN,
	@JsonProperty("Male")   MALE,
	@JsonProperty("Female") FEMALE,
}
