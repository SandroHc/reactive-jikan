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
public abstract class AdvancedSearchQuery<C extends SearchQuery<C,R>, R> extends SearchQuery<C, R> {

	protected AdvancedSearchQuery(Jikan jikan, Type type) {
		super(jikan, type);
	}

	public C rated(AgeRating ageRating) {
		queryParams.put("rated", ageRating.name().toLowerCase());
		return (C) this;
	}

	public C score(float moreThan) throws JikanInvalidArgumentException {
		if (moreThan < 0 || moreThan > 10)
			throw new JikanInvalidArgumentException("score must be between 0.0 and 10.0");

		queryParams.put("score", moreThan);
		return (C) this;
	}

	public C startDate(LocalDate startDate) {
		queryParams.put("startDate", startDate.format(DateTimeFormatter.ISO_DATE));
		return (C) this;
	}

	public C endDate(LocalDate endDate) {
		queryParams.put("endDate", endDate.format(DateTimeFormatter.ISO_DATE));
		return (C) this;
	}

	public C sort(Sort sort) {
		queryParams.put("sort", sort.key);
		return (C) this;
	}

}
