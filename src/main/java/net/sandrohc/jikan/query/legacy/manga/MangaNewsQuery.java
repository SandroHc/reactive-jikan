/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.manga;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.legacy.common.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MangaNewsQuery extends Query<AnimeNewsArticle, Flux<AnimeNewsArticle>> {

	/** The manga ID. */
	private final int id;

	public MangaNewsQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUri() {
		return "/manga/" + id + "/news";
	}

	@Override
	public Class<AnimeNewsArticle> getRequestClass() {
		return AnimeNewsArticle.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return News.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<AnimeNewsArticle> process(Mono<?> content) {
		return ((Mono<News>) content).flatMapMany(results -> Flux.fromIterable(results.articles));
	}
}
