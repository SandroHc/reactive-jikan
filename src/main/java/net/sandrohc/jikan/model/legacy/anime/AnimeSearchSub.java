/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.anime;

import java.time.*;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An anime object, used by the /search endpoint.
 */
public class AnimeSearchSub extends AnimeBase {

	public boolean airing;


	@JsonProperty("start_date")
	public void setStartDate(OffsetDateTime startDate) {
		aired.setFrom(startDate);
	}

	@JsonProperty("end_date")
	public void setEndDate(OffsetDateTime endDate) {
		aired.setTo(endDate);
	}

}
