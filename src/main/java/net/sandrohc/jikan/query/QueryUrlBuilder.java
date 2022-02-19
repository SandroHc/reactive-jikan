/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.function.*;

import net.sandrohc.jikan.exception.JikanUrlException;
import org.slf4j.*;

import static net.sandrohc.jikan.Jikan.JIKAN_MARKER;

/**
 * A query URL builder, with support to query parameters.
 */
public class QueryUrlBuilder {

	private static final Logger log = LoggerFactory.getLogger(QueryUrlBuilder.class);

	protected String path;
	protected final StringBuilder query = new StringBuilder();

	private QueryUrlBuilder() {
	}

	public static QueryUrlBuilder create() {
		return new QueryUrlBuilder();
	}

	public QueryUrlBuilder path(String path) {
		this.path = path;
		return this;
	}

	/**
	 * Add a query parameter, with a value mapper.
	 *
	 * @param key the query parameter key
	 * @param value the query parameter value
	 * @param mapper the value mapper, never receives {@code null} values
	 * @param <T> the query parameter value type
	 * @return the URL builder
	 */
	public <T> QueryUrlBuilder param(String key, T value, Function<T, Object> mapper) {
		return param(key, value != null ? mapper.apply(value) : null);
	}

	/**
	 * Add a query parameter.
	 *
	 * @param key the query parameter key
	 * @param value the query parameter value
	 * @return the URL builder
	 */
	public QueryUrlBuilder param(String key, Object value) {
		if (value == null) {
			log.trace(JIKAN_MARKER, "Ignoring null query parameter '{}'", key);
			return this;
		}

		log.trace(JIKAN_MARKER, "Appending query parameter '{}': {}", key, value);

		// Append the query parameter separator
		if (query.length() > 0)
			query.append('&');

		// TODO: support arrays
		// TODO: url-encode key and value
		if (value instanceof Collection) {
			query.append(key).append("[]=");

			boolean isFirstValue = true;
			for (Object val : ((Collection<?>) value)) {
				if (isFirstValue) {
					isFirstValue = false;
				} else {
					query.append(',');
				}

				query.append(val);
			}
		} else {
			query.append(key).append('=').append(value);
		}

		return this;
	}

	// TODO: confirm that URI query params conform to RFC3986 - http://tools.ietf.org/html/rfc3986#section-2.1 (percent encoding)
	//  Currently doing on QueryableQuery: URLEncoder.encode(query, StandardCharsets.UTF_8.name()).replaceAll("\\+", "%20")
	public URI build() throws JikanUrlException {
		try {
			return new URI(null, null, path, query.toString(), null);
		} catch (URISyntaxException e) {
			throw new JikanUrlException(path, query.toString(), e);
		}
	}
}
