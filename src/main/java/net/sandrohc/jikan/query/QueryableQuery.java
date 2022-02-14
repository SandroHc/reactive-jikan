/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.*;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import org.reactivestreams.Publisher;

/**
 * A queryable query with pagination.
 *
 * @param <T> the response type for this query - used to map the HTTP response
 * @param <R> the result type (can be either a mono or a flux)
 * @param <Q> the final query class
 */
public abstract class QueryableQuery<T, R extends Publisher<?>, Q extends QueryableQuery<T, R, ?>> extends PageableQuery<T, R, Q> {

	/** The query value. */
	protected String query;


	public QueryableQuery(Jikan jikan) {
		super(jikan);
	}

	@SuppressWarnings("unchecked")
	public Q page(Integer page) {
		this.page = page;
		return (Q) this;
	}

	@SuppressWarnings("unchecked")
	public Q query(String query) throws UnsupportedEncodingException, JikanInvalidArgumentException {
		if (query == null) {
			this.query = null;
		} else {
			// TODO: encode on the QueryUrlBuilder
			// encode the string, as per the RFC3986 - http://tools.ietf.org/html/rfc3986#section-2.1 (percent encoding)
			String encoded = URLEncoder.encode(query, StandardCharsets.UTF_8.name()).replaceAll("\\+", "%20");

			this.query = encoded;
		}
		return (Q) this;
	}

	@Override
	public QueryUrl getUrl() {
		return super.getUrl()
				.param("q", query);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[url='" + getUrl() + "', page=" + page + ", limit=" + limit +
				", query='" + query + "']";
	}
}
