/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;

/**
 * A user's favorite anime.
 */
public class UserFavoriteAnime extends EntityWithImage {

	/** The anime type. */
	public AnimeType type;

	/** The anime year of first release. */
	@JsonProperty("start_year")
	public int startYear;


	public AnimeType getType() {
		return type;
	}

	public void setType(AnimeType type) {
		this.type = type;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
}
