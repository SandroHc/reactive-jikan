/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.serializer;

import java.io.*;
import java.time.*;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Converts the number of days into a duration.
 *
 * For example, "1.4" will be converted into "1D 9H 36M"
 */
public class DaysDeserializer extends JsonDeserializer<Duration> {

	private static final int MINUTES_IN_DAY = 24 * 60;

	@Override
	public Duration deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
		if (p.currentToken() != JsonToken.VALUE_NUMBER_FLOAT)
			return null;

		double days = p.getDoubleValue();
		long minutes = (long) (days * MINUTES_IN_DAY);
		return Duration.ofMinutes(minutes);
	}
}
