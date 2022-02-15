/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The user gender.
 */
public enum UserGender {
	@JsonProperty("Male")   MALE,
	@JsonProperty("Female") FEMALE,
	@JsonEnumDefaultValue   UNKNOWN,
}
