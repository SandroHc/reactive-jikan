/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.serializer;

import java.io.*;
import java.time.*;
import java.util.regex.*;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class EpisodeDurationDeserializer extends JsonDeserializer<Duration> {

	// Examples: "23 min", "23 min per ep"
	protected static final Pattern REGEX_DURATION = Pattern.compile("((?<minutes>\\d+) min)");


	@Override
	public Duration deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
		if (p.currentToken() == JsonToken.VALUE_STRING) {
			return parseString(p);
		} else if (p.currentToken() == JsonToken.VALUE_NUMBER_INT) {
			return parseNumber(p);
		} else {
			return null;
		}
	}

	private Duration parseString(JsonParser p) throws IOException {
		String str = p.getText();
		if (str == null)
			return null; // empty string

		Matcher matcher = REGEX_DURATION.matcher(str);
		if (!matcher.find())
			return null; // no match found

		String minutesStr = matcher.group("minutes");
		if (minutesStr == null)
			return null; // no minutes found in string

		int minutes = Integer.parseInt(minutesStr);
		return Duration.ofMinutes(minutes);
	}

	private Duration parseNumber(JsonParser p) throws IOException {
		int number = p.getIntValue();
		return Duration.ofSeconds(number);
	}
}
