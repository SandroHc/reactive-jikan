/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.search;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.*;
import java.time.*;
import java.time.format.*;

import net.sandrohc.jikan.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.enums.*;

@SuppressWarnings("unchecked")
public abstract class AdvancedSearchQuery<C extends SearchQuery<C,R>, R> extends SearchQuery<C, R> {


	protected AdvancedSearchQuery(Type type) {
		super(type);
	}

	public C query(String val) throws UnsupportedEncodingException, JikanInvalidArgumentException {
		if (val.length() < 3)
			throw new JikanInvalidArgumentException("query must be a minimum of 3 characters");

		// encode the string, as per the RFC3986 - http://tools.ietf.org/html/rfc3986#section-2.1 (percent encoding)
		String encoded = URLEncoder.encode(val, StandardCharsets.UTF_8.name()).replaceAll("\\+", "%20");

		queryParams.put("q", encoded);
		return (C) this;
	}

	public C page(int page) {
		queryParams.put("page", page);
		return (C) this;
	}

	public C limit(int limit) throws JikanInvalidArgumentException {
		if (limit < 0 || limit > 50)
			throw new JikanInvalidArgumentException("limit must be between 0 and 50");

		queryParams.put("limit", limit);
		return (C) this;
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
