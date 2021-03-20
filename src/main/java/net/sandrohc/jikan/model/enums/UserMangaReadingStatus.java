/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Reading status for a manga on a user's list.
 */
public enum UserMangaReadingStatus {
	@JsonEnumDefaultValue
	UNKNOWN      (0),
	ALL          (7),
	READING      (1),
	COMPLETED    (2),
	ON_HOLD      (3, "onhold"),
	DROPPED      (4),
	PLAN_TO_READ (6, "plantoread"),
	;

	@JsonValue
	public final int order;
	public final String status;

	UserMangaReadingStatus(int order) {
		this.order = order;
		this.status = this.name().toLowerCase();
	}

	UserMangaReadingStatus(int order, String status) {
		this.order = order;
		this.status = status;
	}

	@JsonCreator
	public static UserMangaReadingStatus fromNumber(int number) {
		for (UserMangaReadingStatus b : UserMangaReadingStatus.values())
			if (b.order == number)
				return b;
		return null;
	}

}
