/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query;

import net.sandrohc.jikan.Jikan;
import org.reactivestreams.Publisher;

/**
 * A pageable query.
 *
 * @param <T> the response type for this query - used to map the HTTP response
 * @param <R> the result type (can be either a mono or a flux)
 * @param <Q> the final query class
 */
public abstract class PageableQuery<T, R extends Publisher<?>, Q extends PageableQuery<T, R, ?>> extends Query<T, R> {

	/** The current page. */
	protected Integer page;

	/** The limit of results. Maximum of 25 results per page. */
	protected Integer limit;


	public PageableQuery(Jikan jikan) {
		super(jikan);
	}

	@SuppressWarnings("unchecked")
	public Q page(Integer page) {
		this.page = page;
		return (Q) this;
	}

	@SuppressWarnings("unchecked")
	public Q limit(Integer limit) {
		this.limit = limit;
		return (Q) this;
	}

	public abstract QueryUrlBuilder getInnerUrl();

	@Override
	public QueryUrlBuilder getUrl() {
		return getInnerUrl()
				.param("page", page)
				.param("limit", limit);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[url='" + getUrl() + "', page=" + page + ", limit=" + limit + ']';
	}
}
