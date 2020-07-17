/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.search;

import java.time.*;
import java.time.format.*;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.enums.*;

@SuppressWarnings("unchecked")
public abstract class AdvancedSearchQuery<QUERY extends SearchQuery<QUERY, TYPE_INITIAL, TYPE_FINAL>, TYPE_INITIAL, TYPE_FINAL> extends SearchQuery<QUERY, TYPE_INITIAL, TYPE_FINAL> {

	protected AdvancedSearchQuery(Jikan jikan, Type type) {
		super(jikan, type);
	}

	public QUERY rated(AgeRating ageRating) {
		queryParams.put("rated", ageRating.name().toLowerCase());
		return (QUERY) this;
	}

	public QUERY score(float moreThan) throws JikanInvalidArgumentException {
		if (moreThan < 0 || moreThan > 10)
			throw new JikanInvalidArgumentException("score must be between 0.0 and 10.0");

		queryParams.put("score", moreThan);
		return (QUERY) this;
	}

	public QUERY startDate(LocalDate startDate) {
		queryParams.put("startDate", startDate.format(DateTimeFormatter.ISO_DATE));
		return (QUERY) this;
	}

	public QUERY endDate(LocalDate endDate) {
		queryParams.put("endDate", endDate.format(DateTimeFormatter.ISO_DATE));
		return (QUERY) this;
	}

	public QUERY sort(Sort sort) {
		queryParams.put("sort", sort.key); // TODO: check if we can use the enum's name
		return (QUERY) this;
	}

}
