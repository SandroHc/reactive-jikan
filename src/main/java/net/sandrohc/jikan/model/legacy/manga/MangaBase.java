/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.manga;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.legacy.enums.*;

/**
 * A manga object containing common fields.
 */
public abstract class MangaBase extends MalEntity {

	/** The official title. */
	public String title;

	/** The URL to the manga on MyAnimeList. */
	public String url;

	/** The banner image URL. */
	@JsonProperty("image_url")
	public String imageUrl;

	/** The current publication status. */
	public MangaStatus status;

	/** The publication type. */
	public MangaType type;

	/** The synopsis. */
	public String synopsis;

	/** The number of volumes. */
	public int volumes;

	/** The number of chapters. */
	public int chapters;

	/** If the manga is still being published. */
	public boolean publishing;

	public DateRange published = new DateRange();

	public float score;

	public int members;


	@Override
	public String toString() {
		return getClass().getSimpleName() + "[id=" + malId + ", title='" + title + "']";
	}

}
