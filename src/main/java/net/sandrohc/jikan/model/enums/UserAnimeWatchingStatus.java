/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserAnimeWatchingStatus {
	@JsonEnumDefaultValue
	UNKNOWN       (0),
	ALL           (7),
	WATCHING      (1),
	COMPLETED     (2),
	ON_HOLD       (3, "onhold"),
	DROPPED       (4),
	PLAN_TO_WATCH (6, "plantowatch"),
	;

	@JsonValue
	public final int order;
	public final String status;

	UserAnimeWatchingStatus(int order) {
		this.order = order;
		this.status = this.name().toLowerCase();
	}

	UserAnimeWatchingStatus(int order, String status) {
		this.order = order;
		this.status = status;
	}

	@JsonCreator
	public static UserAnimeWatchingStatus fromNumber(int number) {
		for (UserAnimeWatchingStatus b : UserAnimeWatchingStatus.values())
			if (b.order == number)
				return b;
		return null;
	}

}
