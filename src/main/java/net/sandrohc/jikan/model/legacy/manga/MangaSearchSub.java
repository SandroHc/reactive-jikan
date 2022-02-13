/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.manga;

import java.time.*;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A manga object, used by the /search endpoint.
 */
public class MangaSearchSub extends MangaBase {


	@JsonProperty("start_date")
	public void setStartDate(OffsetDateTime startDate) {
		published.setFrom(startDate);
	}

	@JsonProperty("end_date")
	public void setEndDate(OffsetDateTime endDate) {
		published.setTo(endDate);
	}

}
