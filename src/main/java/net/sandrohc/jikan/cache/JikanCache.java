/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.cache;

import java.time.*;
import java.util.*;

/**
 * Cache abstraction for the Jikan requests.
 * <p>
 * The cache implementation is left to the developer, as to better suit their needs. Please see
 * <a href="https://github.com/SandroHc/reactive-jikan#caching">https://github.com/SandroHc/reactive-jikan#caching</a>
 * for an example of a caching implementation.
 *
 * @see <a href="https://github.com/SandroHc/reactive-jikan#caching">https://github.com/SandroHc/reactive-jikan#caching</a>
 *      for an example of a caching implementation
 */
public interface JikanCache {

    /**
     * Adds a new key to the cache with an optional expiration timestamp.
     * <p>
     * The cache implementation is not guaranteed to support the expiration timestamp. But as a rule of thumb, it is a
     * good idea to evict cache items that have existed for more than 24 hours.
     *
     * @param key the cache key
     * @param value the value
     * @param expires the expiration date
     */
    void put(String key, Object value, OffsetDateTime expires);

    /**
     * Fetch a key from the cache.
     *
     * @param key the cache key
     * @return the object in the cache
     */
    Optional<Object> get(String key);
}
