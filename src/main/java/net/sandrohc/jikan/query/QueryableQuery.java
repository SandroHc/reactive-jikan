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
	public Q query(String query) {
		this.query = query;
		return (Q) this;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return super.getUrl()
				.param("q", query);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[url='" + getUrl() + "', page=" + page + ", limit=" + limit +
				", query='" + query + "']";
	}
}
