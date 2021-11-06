/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.search;

import java.util.*;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.enums.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AnimeSearchQuery extends AdvancedSearchQuery<AnimeSearchQuery, AnimeSearchSub> {

	public AnimeSearchQuery(Jikan jikan) {
		super(jikan, Type.ANIME);
	}

	public AnimeSearchQuery status(AnimeStatus status) {
		queryParams.put("status", status.name().toLowerCase());
		return this;
	}

	@SuppressWarnings("unchecked")
	public AnimeSearchQuery genres(AnimeGenre... genres) {
		Collection<Integer> genreList = (Collection<Integer>) queryParams.computeIfAbsent("genre", key -> new TreeSet<>());

		for (AnimeGenre genre : genres)
			genreList.add(genre.ordinal());

		return this;
	}

	/**
	 * To exclude the genre you added in your request.
	 * @return anime search query
	 */
	public AnimeSearchQuery excludeGivenGenres() {
		queryParams.putIfAbsent("genre_exclude", 1);
		return this;
	}

	/**
	 * To include the genre you added in your request.
	 * @return anime search query
	 */
	public AnimeSearchQuery includeGivenGenres() {
		queryParams.putIfAbsent("genre_exclude", 0);
		return this;
	}

	public AnimeSearchQuery orderBy(AnimeOrderBy orderBy) {
		queryParams.put("orderBy", orderBy.name().toLowerCase());
		return this;
	}

	@Override
	public Class<AnimeSearchSub> getRequestClass() {
		return AnimeSearchSub.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return AnimeSearch.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<AnimeSearchSub> process(Mono<?> content) {
		return ((Mono<AnimeSearch>) content).flatMapMany(search -> Flux.fromIterable(search.results));
	}

}
