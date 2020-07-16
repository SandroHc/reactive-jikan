/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.search;

import java.util.*;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.search.*;

public class MangaSearchQuery extends AdvancedSearchQuery<MangaSearchQuery, MangaSearch> { // TODO: create a MangaSub variant

	public MangaSearchQuery(Jikan jikan) {
		super(jikan, Type.MANGA);
	}

	public MangaSearchQuery status(MangaStatus status) {
		queryParams.put("status", status.name().toLowerCase());
		return this;
	}

	@SuppressWarnings("unchecked")
	public MangaSearchQuery genres(MangaGenre... genres) {
		// TODO: check if we need to include the 'gender_exclude' in the request
		Set<Integer> genreList = (Set<Integer>) queryParams.computeIfAbsent("genre", key -> new TreeSet<>());

		for (MangaGenre genre : genres)
			genreList.add(genre.ordinal());

		return this;
	}

	public MangaSearchQuery orderBy(MangaOrderBy orderBy) {
		queryParams.put("orderBy", orderBy.name().toLowerCase());
		return this;
	}

	@Override
	public Class<MangaSearch> getRequestClass() {
		return MangaSearch.class;
	}

}
