/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import java.util.*;
import java.util.stream.*;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import net.sandrohc.jikan.query.QueryableQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the anime search.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeSearch">Jikan API docs - getAnimeSearch</a>
 */
public class AnimeSearchQuery extends QueryableQuery<DataListHolderWithPagination<Anime>, Flux<Anime>, AnimeSearchQuery> {

	protected AnimeType type;
	protected Double score;
	protected Double minimumScore;
	protected Double maximumScore;
	protected AnimeStatus status;
	protected AgeRating ageRating;
	protected Boolean sfw;
	protected Genre[] genres;
	protected Genre[] genresExclude;
	protected AnimeOrderBy orderBy;
	protected SortOrder sort;
	protected String suffix;
	protected Integer[] producerIds;


	public AnimeSearchQuery(Jikan jikan) {
		super(jikan);
	}

	public AnimeSearchQuery type(AnimeType type) {
		this.type = type;
		return this;
	}

	public AnimeSearchQuery score(Double moreThan) {
		this.score = moreThan;
		return this;
	}

	public AnimeSearchQuery minimumScore(Double minScore) {
		this.minimumScore = minScore;
		return this;
	}

	public AnimeSearchQuery maximumScore(Double maxScore) {
		this.maximumScore = maxScore;
		return this;
	}

	public AnimeSearchQuery status(AnimeStatus status) {
		this.status = status;
		return this;
	}

	public AnimeSearchQuery rating(AgeRating ageRating) {
		this.ageRating = ageRating;
		return this;
	}

	public AnimeSearchQuery safeForWork(Boolean sfwOnly) {
		this.sfw = sfwOnly;
		return this;
	}

	public AnimeSearchQuery genres(Genre... genres) {
		this.genres = genres;
		return this;
	}

	public AnimeSearchQuery excludeGenres(Genre... excludeGenres) {
		this.genresExclude = excludeGenres;
		return this;
	}

	public AnimeSearchQuery orderBy(AnimeOrderBy orderBy, SortOrder sortOrder) {
		this.orderBy = orderBy;
		this.sort = sortOrder;
		return this;
	}

	public AnimeSearchQuery suffix(String suffix) {
		this.suffix = suffix;
		return this;
	}

	public AnimeSearchQuery producers(Integer... producerIds) {
		this.producerIds = producerIds;
		return this;
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create()
				.path("/anime")
				.param("type", type, AnimeType::getSearch)
				.param("score", score)
				.param("min_score", minimumScore)
				.param("max_score", maximumScore)
				.param("status", status, AnimeStatus::getSearch)
				.param("rating", ageRating, AgeRating::getSearch)
				.param("sfw", sfw)
				.param("genres", genres, AnimeSearchQuery::extractGenreIds)
				.param("genres_exclude", genresExclude, AnimeSearchQuery::extractGenreIds)
				.param("order_by", orderBy, AnimeOrderBy::getSearch)
				.param("sort", sort, SortOrder::getSearch)
				.param("letter", suffix)
				.param("producer", producerIds);
	}

	@Override
	public Flux<Anime> process(Mono<DataListHolderWithPagination<Anime>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}

	protected static Collection<Integer> extractGenreIds(Genre[] genres) {
		if (genres == null)
			return Collections.emptyList();

		return Collections.unmodifiableCollection(Arrays.stream(genres)
				.map(Genre::animeId)
				.collect(Collectors.toCollection(TreeSet::new)));
	}
}
