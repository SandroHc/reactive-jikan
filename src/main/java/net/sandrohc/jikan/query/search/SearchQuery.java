/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.search;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.*;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;

@SuppressWarnings("unchecked")
public abstract class SearchQuery<Q extends SearchQuery<Q,T>, T> extends Query<T, Flux<T>> {

	public static final int LIMIT_MAX = 50;

	/** The type of the results. */
	public final Type type;


	protected SearchQuery(Jikan jikan, Type type) {
		super(jikan);
		this.type = type;
	}

	public Q query(String val) throws UnsupportedEncodingException, JikanInvalidArgumentException {
		if (val.length() < 3)
			throw new JikanInvalidArgumentException("query must be a minimum of 3 characters");

		// encode the string, as per the RFC3986 - http://tools.ietf.org/html/rfc3986#section-2.1 (percent encoding)
		String encoded = URLEncoder.encode(val, StandardCharsets.UTF_8.name()).replaceAll("\\+", "%20");

		queryParams.put("q", encoded);
		return (Q) this;
	}

	public Q page(int page) {
		queryParams.put("page", page);
		return (Q) this;
	}

	public Q limit(int limit) throws JikanInvalidArgumentException {
		if (limit < 0 || limit > LIMIT_MAX)
			throw new JikanInvalidArgumentException("limit must be between 0 and " + LIMIT_MAX);

		queryParams.put("limit", limit);
		return (Q) this;
	}

	@Override
	public String getUri() {
		return "/search/" + type.search;
	}

}
