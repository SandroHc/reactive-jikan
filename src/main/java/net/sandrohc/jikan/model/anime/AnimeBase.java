/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.enums.*;

/**
 * An anime object containing common fields.
 */
public class AnimeBase extends MalEntity {

	/** The official title. */
	public String title;

	/** The page URL on MyAnimeList. */
	public String url;

	/** The banner image URL. */
	@JsonProperty("image_url")
	public String imageUrl;

	public AnimeType type;

	public String synopsis;

	public int episodes;

	public DateRange aired = new DateRange();

	/** The anime age rating. */
	@JsonAlias("rated")
	public AgeRating rating;

	/** The anime score, in the range of 0.00 to 10.00. */
	public float score;

	public int members;


	@Override
	public String toString() {
		return getClass().getSimpleName() + "[id=" + malId + ", title='" + title + "']";
	}

}
