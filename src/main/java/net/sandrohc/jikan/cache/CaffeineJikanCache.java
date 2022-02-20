/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.cache;

import java.time.*;
import java.util.*;
import java.util.concurrent.*;
import com.github.benmanes.caffeine.cache.*;
import org.slf4j.*;

/**
 * An implementation of the Jikan cache using the performant Caffeine library.
 */
public class CaffeineJikanCache implements JikanCache {

    protected final Logger log = LoggerFactory.getLogger(CaffeineJikanCache.class);
    protected final Cache<String, ValueHolder> cache;

    /**
     * Customizable cache. Build via {@code Caffeine.newBuilder()}.
     */
    public CaffeineJikanCache(Caffeine<Object, Object> cache) {
        this.cache = cache.expireAfter(new JikanExpiry()).build();
    }

    /**
     * Prepares a cache that stores up to 10'000 records.
     */
    public CaffeineJikanCache() {
        this(Caffeine.newBuilder().maximumSize(10_000));
    }

    @Override
    public void put(String key, Object value, OffsetDateTime expires) {
        log.trace("Storing in cache '{}' that expires at {}", key, expires);
        cache.put(key, new ValueHolder(value, expires));
    }

    @Override
    public Optional<Object> get(String key) {
        return Optional.ofNullable(cache.getIfPresent(key)).map(holder -> holder.value);
    }


    private static class ValueHolder {
        public final Object value;
        public final long expireTime;

        public ValueHolder(Object value, OffsetDateTime expires) {
            this.value = value;
            this.expireTime = TimeUnit.SECONDS.toNanos(expires.toEpochSecond());
        }
    }

    private static class JikanExpiry implements Expiry<String, ValueHolder> {
        @Override
        public long expireAfterCreate(String key, ValueHolder value, long currentTime) {
            return value.expireTime;
        }

        @Override
        public long expireAfterUpdate(String key, ValueHolder value, long currentTime, long currentDuration) {
            return currentDuration; // Do not modify expire date
        }

        @Override
        public long expireAfterRead(String key, ValueHolder value, long currentTime, long currentDuration) {
            return currentDuration; // Do not modify expire date
        }
    }

}
