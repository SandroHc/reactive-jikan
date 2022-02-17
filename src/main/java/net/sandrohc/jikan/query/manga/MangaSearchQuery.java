/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.manga.MangaOrderBy;
import net.sandrohc.jikan.model.manga.*;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import net.sandrohc.jikan.query.QueryableQuery;
import net.sandrohc.jikan.utils.EnumUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Query for the manga search.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMangaSearch">Jikan API docs - getMangaSearch</a>
 */
public class MangaSearchQuery extends QueryableQuery<DataListHolderWithPagination<Manga>, Flux<Manga>, MangaSearchQuery> {

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
	public QueryUrlBuilder getInnerUrl() {
		return QueryUrlBuilder.endpoint("/manga")
				.param("type", type.search)
				.param("score", score)
				.param("min_score", minimumScore)
				.param("max_score", maximumScore)
				.param("status", status.search)
				.param("sfw", sfw)
				.param("genres", genres, EnumUtil::enumsToOrdinals)
				.param("genres_exclude", genresExclude, EnumUtil::enumsToOrdinals)
				.param("order_by", orderBy, MangaOrderBy::getSearch)
				.param("sort", sort, SortOrder::getSearch)
				.param("letter", suffix)
				.param("magazine", magazineIds);
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
