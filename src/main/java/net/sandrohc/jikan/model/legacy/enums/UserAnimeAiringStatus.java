/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Airing status of an anime on a user's list.
 */
public enum UserAnimeAiringStatus {
	@JsonEnumDefaultValue
	UNKNOWN       (0),
	AIRING        (1),
	AIRED         (2),
	NOT_YET_AIRED (3),
	;

	@JsonValue
	public final int order;

	UserAnimeAiringStatus(int order) {
		this.order = order;
	}

	@JsonCreator
	public static UserAnimeAiringStatus fromNumber(int number) {
		for (UserAnimeAiringStatus b : UserAnimeAiringStatus.values())
			if (b.order == number)
				return b;
		return null;
	}

}
