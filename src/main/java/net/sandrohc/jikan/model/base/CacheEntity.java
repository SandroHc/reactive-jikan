/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.base;

import java.io.*;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class CacheEntity implements Serializable {

	@JsonProperty("request_hash")
	public String requestHash;

	@JsonProperty("request_cached")
	public boolean requestCached;

	@JsonProperty("request_cache_expiry")
	public int requestCacheExpiry;

}
