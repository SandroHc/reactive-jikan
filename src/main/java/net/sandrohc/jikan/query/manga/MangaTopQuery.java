/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.manga.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Get the most popular manga by score.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getTopManga">Jikan API docs - getTopManga</a>
 */
public class MangaTopQuery extends PageableQuery<DataListHolderWithPagination<Manga>, Flux<Manga>, MangaTopQuery> {

	public MangaTopQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return endpoint("/top/manga");
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
