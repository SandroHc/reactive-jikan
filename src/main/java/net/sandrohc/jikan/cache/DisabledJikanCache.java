/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.cache;

import java.time.*;
import java.util.*;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A dummy implementation of the Jikan cache that disables caching altogether.
 */
public class DisabledJikanCache implements JikanCache {
	@Override
	public void put(String key, Object value, OffsetDateTime expires) {	}

	@Override
	public @Nullable Optional<Object> get(String key) {
		return Optional.empty();
	}
}
