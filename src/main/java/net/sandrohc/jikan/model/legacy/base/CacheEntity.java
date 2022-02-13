/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.base;

import java.io.*;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A Jikan entity with caching information.
 */
public abstract class CacheEntity implements Serializable {

	/**
	 * The request hashcode.
	 */
	@JsonProperty("request_hash")
	public String requestHash;

	/** Whether the request was served from the Jikan cache or not. */
	@JsonProperty("request_cached")
	public boolean requestCached;

	/** Time in seconds for the request to expire. */
	@JsonProperty("request_cache_expiry")
	public int requestCacheExpiry;

}
