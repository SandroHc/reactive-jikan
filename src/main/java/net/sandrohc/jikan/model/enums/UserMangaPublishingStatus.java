/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserMangaPublishingStatus {
	@JsonEnumDefaultValue
	UNKNOWN           (0),
	PUBLISHING        (1),
	PUBLISHED         (2),
	NOT_YET_PUBLISHED (3),
	ON_HIATUS         (4),
	DISCONTINUED      (5),
	;

	@JsonValue
	public final int order;

	UserMangaPublishingStatus(int order) {
		this.order = order;
	}

	@JsonCreator
	public static UserMangaPublishingStatus fromNumber(int number) {
		for (UserMangaPublishingStatus b : UserMangaPublishingStatus.values())
			if (b.order == number)
				return b;
		return null;
	}

}
