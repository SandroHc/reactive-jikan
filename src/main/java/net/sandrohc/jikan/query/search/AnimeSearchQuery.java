/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.search;

import java.util.*;

import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.search.*;

public class AnimeSearchQuery extends AdvancedSearchQuery<AnimeSearchQuery, AnimeSearch> {

	public AnimeSearchQuery() {
		super(Type.ANIME);
	}

	public AnimeSearchQuery status(AnimeStatus status) {
		queryParams.put("status", status.name().toLowerCase());
		return this;
	}

	@SuppressWarnings("unchecked")
	public AnimeSearchQuery genres(AnimeGenre... genres) {
		// TODO: check if we need to include the 'gender_exclude' in the request
		Set<Integer> genreList = (Set<Integer>) queryParams.computeIfAbsent("genre", key -> new TreeSet<>());

		for (AnimeGenre genre : genres)
			genreList.add(genre.ordinal());

		return this;
	}

	public AnimeSearchQuery orderBy(AnimeOrderBy orderBy) {
		queryParams.put("orderBy", orderBy.name().toLowerCase());
		return this;
	}

	@Override
	public Class<AnimeSearch> getRequestClass() {
		return AnimeSearch.class;
	}

}
