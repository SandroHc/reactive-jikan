/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.manga;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.enums.*;

/**
 * A manga object containing common fields.
 */
public abstract class MangaBase extends MalEntity {

	/** The official title. */
	public String title;

	/** The page URL on MyAnimeList. */
	public String url;

	/** The banner image URL. */
	@JsonProperty("image_url")
	public String imageUrl;

	public MangaStatus status;

	public MangaType type;

	public String synopsis;

	public int volumes;

	public int chapters;

	public boolean publishing;

	public DateRange published = new DateRange();

	public float score;

	public int members;


	@Override
	public String toString() {
		return getClass().getSimpleName() + "[id=" + malId + ", title='" + title + "']";
	}

}
