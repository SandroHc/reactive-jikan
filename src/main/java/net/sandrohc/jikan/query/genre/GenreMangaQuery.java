/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.genre;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.genre.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the manga genre search.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMangaGenres">Jikan API docs - getMangaGenres</a>
 */
public class GenreMangaQuery extends PageableQuery<DataListHolder<GenreWithCount>, Flux<GenreWithCount>, GenreMangaQuery> {

	protected GenreType type;

	public GenreMangaQuery(Jikan jikan) {
		super(jikan);
	}

	public GenreMangaQuery type(GenreType type) {
		this.type = type;
		return this;
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create()
				.path("/genres/manga")
				.param("filter", type, GenreType::getSearch);
	}

	@Override
	public Flux<GenreWithCount> process(Mono<DataListHolder<GenreWithCount>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
