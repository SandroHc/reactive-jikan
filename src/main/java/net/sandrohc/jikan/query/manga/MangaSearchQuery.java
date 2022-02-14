/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.*;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.manga.MangaOrderBy;
import net.sandrohc.jikan.model.legacy.enums.*;
import net.sandrohc.jikan.model.manga.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.utils.EnumUtil.enumsToOrdinals;

/**
 * Query for the manga search.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMangaSearch">Jikan API docs - getMangaSearch</a>
 */
public class MangaSearchQuery extends Query<DataListHolderWithPagination<Manga>, Flux<Manga>> {

	public static final int LIMIT_MAX = 50;

	protected Integer page;
	protected Integer limit;
	protected String query;
	protected MangaType type;
	protected Double score;
	protected Double minimumScore;
	protected Double maximumScore;
	protected MangaStatus status;
	protected Boolean sfw;
	protected MangaGenre[] genres;
	protected MangaGenre[] genresExclude;
	protected MangaOrderBy orderBy;
	protected SortOrder sort;
	protected String suffix;
	protected Integer[] magazineIds;


	public MangaSearchQuery(Jikan jikan) {
		super(jikan);
	}

	public MangaSearchQuery page(Integer page) {
		this.page = page;
		return this;
	}

	public MangaSearchQuery limit(Integer limit) throws JikanInvalidArgumentException {
		if (limit != null && (limit < 0 || limit > LIMIT_MAX))
			throw new JikanInvalidArgumentException("limit must be between 0 and " + LIMIT_MAX);

		this.limit = limit;
		return this;
	}

	public MangaSearchQuery query(String query) throws UnsupportedEncodingException, JikanInvalidArgumentException {
		if (query == null)
			query = "";

		// TODO: encode on the QueryUrlBuilder
		// encode the string, as per the RFC3986 - http://tools.ietf.org/html/rfc3986#section-2.1 (percent encoding)
		String encoded = URLEncoder.encode(query, StandardCharsets.UTF_8.name()).replaceAll("\\+", "%20");

		this.query = encoded;
		return this;
	}

	public MangaSearchQuery type(MangaType type) {
		this.type = type;
		return this;
	}

	public MangaSearchQuery score(Double moreThan) throws JikanInvalidArgumentException {
		if (moreThan != null && (moreThan < 0 || moreThan > 10))
			throw new JikanInvalidArgumentException("score must be between 0.0 and 10.0");

		this.score = moreThan;
		return this;
	}

	public MangaSearchQuery minimumScore(Double minScore) {
		this.minimumScore = minScore;
		return this;
	}

	public MangaSearchQuery maximumScore(Double maxScore) {
		this.maximumScore = maxScore;
		return this;
	}

	public MangaSearchQuery status(MangaStatus status) {
		this.status = status;
		return this;
	}

	public MangaSearchQuery safeForWork(Boolean sfwOnly) {
		this.sfw = sfwOnly;
		return this;
	}

	public MangaSearchQuery genres(MangaGenre... genres) {
		this.genres = genres;
		return this;
	}

	public MangaSearchQuery excludeGenres(MangaGenre... excludeGenres) {
		this.genresExclude = excludeGenres;
		return this;
	}

	public MangaSearchQuery orderBy(MangaOrderBy orderBy, SortOrder sortOrder) {
		this.orderBy = orderBy;
		this.sort = sortOrder;
		return this;
	}

	public MangaSearchQuery suffix(String suffix) {
		this.suffix = suffix;
		return this;
	}

	public MangaSearchQuery magazines(Integer... magazineIds) {
		this.magazineIds = magazineIds;
		return this;
	}

	@Override
	public String getUrl() {
		QueryUrlBuilder builder = QueryUrlBuilder.endpoint("/manga");
		if (page != null) builder.queryParam("page", page);
		if (limit != null) builder.queryParam("limit", limit);
		if (query != null) builder.queryParam("q", query);
		if (type != null) builder.queryParam("type", type.search);
		if (score != null) builder.queryParam("score", score);
		if (minimumScore != null) builder.queryParam("min_score", minimumScore);
		if (maximumScore != null) builder.queryParam("max_score", maximumScore);
		if (status != null) builder.queryParam("status", status.search);
		if (sfw != null) builder.queryParam("sfw", sfw);
		if (genres != null && genres.length > 0) builder.queryParam("genres", enumsToOrdinals(genres));
		if (genresExclude != null && genresExclude.length > 0) builder.queryParam("genres_exclude", enumsToOrdinals(genresExclude));
		if (orderBy != null) builder.queryParam("order_by", orderBy.search);
		if (sort != null) builder.queryParam("sort", sort.search);
		if (suffix != null) builder.queryParam("letter", suffix);
		if (magazineIds != null && magazineIds.length > 0) builder.queryParam("magazine", magazineIds);
		return builder.build();
	}

	@Override
	public TypeReference<DataListHolderWithPagination<Manga>> getResponseType() {
		return new TypeReference<DataListHolderWithPagination<Manga>>() { };
	}

	@Override
	public Flux<Manga> process(Mono<DataListHolderWithPagination<Manga>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
