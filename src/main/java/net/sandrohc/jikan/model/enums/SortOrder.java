/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

public enum SortOrder {
	ASCENDING  ("asc"),
	DESCENDING ("desc");


	public final String value;

	SortOrder(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
