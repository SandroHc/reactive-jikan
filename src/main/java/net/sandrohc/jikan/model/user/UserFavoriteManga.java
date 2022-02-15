/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.manga.*;

/**
 * A user's favorite manga.
 */
public class UserFavoriteManga extends EntityWithImage {

	/** The manga type. */
	public MangaType type;

	/** The manga year of first release. */
	@JsonProperty("start_year")
	public int startYear;


	public MangaType getType() {
		return type;
	}

	public void setType(MangaType type) {
		this.type = type;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
}
