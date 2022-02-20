/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.serializer;

import java.io.*;
import java.util.regex.*;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

public class StringDeserializer extends com.fasterxml.jackson.databind.deser.std.StringDeserializer {

	// List of Unicode categories: https://www.regular-expressions.info/unicode.html
	protected static final Pattern REGEX_NON_PRINTABLE = Pattern.compile("[\\p{Cc}\\p{Cf}\\p{Co}\\p{Cn}]");
	protected static final Pattern REGEX_WHITESPACE = Pattern.compile("\\p{Zs}");


	@Override
	public String deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
		String text = super.deserialize(p, ctx);
		return cleanText(text);
	}

	private static String cleanText(String text) {
		if (text == null)
			return null;

		String clean;
		clean = REGEX_NON_PRINTABLE.matcher(text).replaceAll("");
		clean = REGEX_WHITESPACE.matcher(clean).replaceAll(" ");
		clean = clean.trim();

		return clean;
	}
}
