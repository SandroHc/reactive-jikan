/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.serializer;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import net.sandrohc.jikan.model.anime.*;

public class SongDeserializer extends JsonDeserializer<Song> {

	// Examples: 1: "Yume Sekai (ユメセカイ)" by Haruka Tomatsu (eps 2-14)
	protected static final Pattern REGEX_SONG = Pattern.compile("(?<number>\\d+):[  ]\"(?<name>.*?)\" by (?<author>.*?)[  ]\\(eps (?<episodes>\\d+(?:-\\d+)?)\\)");


	@Override
	public Song deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
		String str = p.getText();
		if (str == null)
			return null; // empty string

		Matcher matcher = REGEX_SONG.matcher(str);
		if (!matcher.find())
			return null; // no match found

		String numberStr = matcher.group("number");
		String name = matcher.group("name");
		String author = matcher.group("author");
		String episodes = matcher.group("episodes");

		Song song = new Song();
		song.setNumber(getNumber(numberStr));
		song.setName(name);
		song.setArtist(author);
		song.setEpisodes(getEpisodeRange(episodes));

		return song;
	}

	private int getNumber(String value) {
		return value != null ? Integer.parseInt(value) : 0;
	}

	private Collection<Integer> getEpisodeRange(String value) {
		if (value == null || value.isEmpty())
			return Collections.emptyList(); // empty value

		Collection<Integer> values;

		// Check if range, e.g. "1-10"
		int rangeSepIndex = value.indexOf('-');
		if (rangeSepIndex != -1) {
			int from = Integer.parseInt(value.substring(0, rangeSepIndex));
			int to = Integer.parseInt(value.substring(rangeSepIndex + 1));

			return IntStream.range(from, to + 1).boxed().collect(Collectors.toList());
		}

		// Single episode
		int episode = Integer.parseInt(value);
		return Collections.singletonList(episode);
	}
}
