/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.search;

import java.util.*;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.manga.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// TODO: create a MangaSub variant
public class MangaSearchQuery extends AdvancedSearchQuery<MangaSearchQuery, MangaSearchSub> {

	public MangaSearchQuery(Jikan jikan) {
		super(jikan, Type.MANGA);
	}

	public MangaSearchQuery status(MangaStatus status) {
		queryParams.put("status", status.name().toLowerCase());
		return this;
	}

	@SuppressWarnings("unchecked")
	public MangaSearchQuery genres(MangaGenre... genres) {
		Collection<Integer> genreList = (Collection<Integer>) queryParams.computeIfAbsent("genre", key -> new TreeSet<>());

		for (MangaGenre genre : genres)
			genreList.add(genre.ordinal());

		return this;
	}

	/**
	 * To exclude the genre you added in your request.
	 * @return manga search query
	 */
	public MangaSearchQuery excludeGivenGenres() {
		queryParams.putIfAbsent("genre_exclude", 0);
		return this;
	}

	/**
	 * To include the genre you added in your request.
	 * @return manga search query
	 */
	public MangaSearchQuery includeGivenGenres() {
		queryParams.putIfAbsent("genre_exclude", 1);
		return this;
	}

	public MangaSearchQuery orderBy(MangaOrderBy orderBy) {
		queryParams.put("orderBy", orderBy.name().toLowerCase());
		return this;
	}

	@Override
	public Class<MangaSearchSub> getRequestClass() {
		return MangaSearchSub.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return MangaSearch.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<MangaSearchSub> process(Mono<?> content) {
		return ((Mono<MangaSearch>) content).flatMapMany(search -> Flux.fromIterable(search.results));
	}

}
