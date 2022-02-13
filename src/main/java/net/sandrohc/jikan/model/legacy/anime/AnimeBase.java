/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.anime;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.legacy.enums.*;

/**
 * An anime object containing common fields.
 */
public class AnimeBase extends MalEntity {

	/** The official title. */
	public String title;

	/** The URL to the anime page on MyAnimeList. */
	public String url;

	/** The banner image URL. */
	@JsonProperty("image_url")
	public String imageUrl;

	/** The publication type. */
	public AnimeType type;

	/** The synopsis. */
	public String synopsis;

	/** The number of episodes. Can be {@code null} if it is unknown. */
	public Integer episodes;

	/** The airing start and end dates. */
	public DateRange aired = new DateRange();

	/** The anime age rating. */
	@JsonAlias("rated")
	public AgeRating rating;

	/** The anime score, in the range of 0.00 to 10.00. */
	public float score;

	/** The number of users that added this anime to their list. */
	public int members;


	@Override
	public String toString() {
		return getClass().getSimpleName() + "[id=" + malId + ", title='" + title + "']";
	}

}
