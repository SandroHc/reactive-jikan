/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

public enum SortOrder {
	ASCENDING  ("asc"),
	DESCENDING ("desc");


	/** Used in the search queries. */
	public final String search;

	SortOrder(String value) {
		this.search = value;
	}

	public String getSearch() {
		return search;
	}
}
