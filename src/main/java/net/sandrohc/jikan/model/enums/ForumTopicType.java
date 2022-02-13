/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum ForumTopicType {
	@JsonProperty("all")     ALL     ("all"),
	@JsonProperty("episode") EPISODE ("episode"),
	@JsonProperty("other")   OTHER   ("other"),
	@JsonEnumDefaultValue    UNKNOWN (null);


	public final String value;

	ForumTopicType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
