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
public abstract class AdvancedSearchQuery<Q extends AdvancedSearchQuery<Q, T>, T> extends SearchQuery<Q, T> {

	protected AdvancedSearchQuery(Jikan jikan, Type type) {
		super(jikan, type);
	}

	public Q rated(AgeRating ageRating) {
		queryParams.put("rated", ageRating.name().toLowerCase());
		return (Q) this;
	}

	public Q score(float moreThan) throws JikanInvalidArgumentException {
		if (moreThan < 0 || moreThan > 10)
			throw new JikanInvalidArgumentException("score must be between 0.0 and 10.0");

		queryParams.put("score", moreThan);
		return (Q) this;
	}

	public Q startDate(LocalDate startDate) {
		queryParams.put("startDate", startDate.format(DateTimeFormatter.ISO_DATE));
		return (Q) this;
	}

	public Q endDate(LocalDate endDate) {
		queryParams.put("endDate", endDate.format(DateTimeFormatter.ISO_DATE));
		return (Q) this;
	}

	public Q sort(Sort sort) {
		queryParams.put("sort", sort.key);
		return (Q) this;
	}

}
