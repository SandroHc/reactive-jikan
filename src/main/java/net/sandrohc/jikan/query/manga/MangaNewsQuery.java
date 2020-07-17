/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.QueryFlux;
import net.sandrohc.jikan.query.QueryMono;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MangaNewsQuery extends QueryFlux<News, NewsArticle> {

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
	public Class<News> getRequestClass() {
		return News.class;
	}

	@Override
	public Flux<NewsArticle> process(Mono<News> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.articles));
	}
}
