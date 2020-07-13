/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.search;

import java.time.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.enums.*;

public class AnimeSearchSub extends MalEntity {

	public String url;

	@JsonProperty("image_url")
	public String imageUrl;

	public String title;

	public boolean airing;

	public String synopsis;

	public AnimeType type;

	public int episodes;

	public float score;

	public int members;

	@JsonProperty("start_date")
	public OffsetDateTime startDate;

	@JsonProperty("end_date")
	public OffsetDateTime endDate;

	public AgeRating rated;



	@Override
	public String toString() {
		return "AnimeSearchSub[" +
			   "id=" + malId +
			   ", title='" + title + '\'' +
			   ']';
	}

}
