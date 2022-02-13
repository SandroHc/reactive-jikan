/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.*;
import java.util.*;
import java.util.stream.*;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.legacy.enums.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Query for the anime search.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeSearch">Jikan API docs - getAnimeSearch</a>
 */
public class AnimeSearchQuery extends Query<DataListHolderWithPagination<Anime>, Flux<Anime>> {

	public static final int LIMIT_MAX = 50;

	protected Integer page;
	protected Integer limit;
	protected String query;
	protected AnimeType type;
	protected Double score;
	protected Double minimumScore;
	protected Double maximumScore;
	protected AnimeStatus status;
	protected AgeRating ageRating;
	protected Boolean sfw;
	protected AnimeGenre[] genres;
	protected AnimeGenre[] genresExclude;
	protected AnimeOrderBy orderBy;
	protected SortOrder sort;
	protected String suffix;
	protected Integer[] producerIds;


	public AnimeSearchQuery(Jikan jikan) {
		super(jikan);
	}

	public AnimeSearchQuery page(Integer page) {
		this.page = page;
		return this;
	}

	public AnimeSearchQuery limit(Integer limit) throws JikanInvalidArgumentException {
		if (limit != null && (limit < 0 || limit > LIMIT_MAX))
			throw new JikanInvalidArgumentException("limit must be between 0 and " + LIMIT_MAX);

		this.limit = limit;
		return this;
	}

	public AnimeSearchQuery query(String query) throws UnsupportedEncodingException, JikanInvalidArgumentException {
		if (query == null)
			query = "";

		// TODO: encode on the QueryUrlBuilder
		// encode the string, as per the RFC3986 - http://tools.ietf.org/html/rfc3986#section-2.1 (percent encoding)
		String encoded = URLEncoder.encode(query, StandardCharsets.UTF_8.name()).replaceAll("\\+", "%20");

		this.query = encoded;
		return this;
	}

	public AnimeSearchQuery type(AnimeType animeType) {
		this.type = animeType;
		return this;
	}

	public AnimeSearchQuery score(Double moreThan) throws JikanInvalidArgumentException {
		if (moreThan != null && (moreThan < 0 || moreThan > 10))
			throw new JikanInvalidArgumentException("score must be between 0.0 and 10.0");

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

	public AnimeSearchQuery genres(AnimeGenre... genres) {
		this.genres = genres;
		return this;
	}

	public AnimeSearchQuery excludeGenres(AnimeGenre... excludeGenres) {
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
	public String getUrl() {
		QueryUrlBuilder builder = QueryUrlBuilder.endpoint("/anime");
		if (page != null) builder.queryParam("page", page);
		if (limit != null) builder.queryParam("limit", limit);
		if (query != null) builder.queryParam("q", query);
		if (type != null) builder.queryParam("type", type.name().toLowerCase()); // TODO: add 'searchValue/queryParam' field to enum
		if (score != null) builder.queryParam("score", score);
		if (minimumScore != null) builder.queryParam("min_score", minimumScore);
		if (maximumScore != null) builder.queryParam("max_score", maximumScore);
		if (status != null) builder.queryParam("status", status.name().toLowerCase()); // TODO: add 'searchValue/queryParam' field to enum
		if (ageRating != null) builder.queryParam("rating", ageRating.name().toLowerCase()); // TODO: add 'searchValue/queryParam' field to enum
		if (sfw != null) builder.queryParam("sfw", sfw);
		if (genres != null && genres.length > 0) builder.queryParam("genres", genresToIds(genres));
		if (genresExclude != null && genresExclude.length > 0) builder.queryParam("genres_exclude", genresToIds(genresExclude));
		if (orderBy != null) builder.queryParam("order_by", orderBy.name().toLowerCase()); // TODO: add 'searchValue/queryParam' field to enum
		if (sort != null) builder.queryParam("sort", sort.value);
		if (suffix != null) builder.queryParam("letter", suffix);
		if (producerIds != null && producerIds.length > 0) builder.queryParam("producer", producerIds);
		return builder.build();
	}

	@Override
	public TypeReference<DataListHolderWithPagination<Anime>> getResponseType() {
		return new TypeReference<DataListHolderWithPagination<Anime>>() { };
	}

	@Override
	public Flux<Anime> process(Mono<DataListHolderWithPagination<Anime>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}

	private static Collection<Integer> genresToIds(AnimeGenre[] excludeGenres) {
		if (excludeGenres == null)
			return Collections.emptyList();

		return Collections.unmodifiableCollection(Arrays.stream(excludeGenres)
				.map(Enum::ordinal)
				.collect(Collectors.toCollection(TreeSet::new)));
	}

}
