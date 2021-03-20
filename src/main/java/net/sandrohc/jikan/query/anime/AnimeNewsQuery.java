/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AnimeNewsQuery extends Query<NewsArticle, Flux<NewsArticle>> {

	/** The anime ID. */
	private final int id;

	public AnimeNewsQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUri() {
		return "/anime/" + id + "/news";
	}

	@Override
	public Class<NewsArticle> getRequestClass() {
		return NewsArticle.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return News.class;
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public Flux<NewsArticle> process(Mono<?> content) {
		return ((Mono<News>) content).flatMapMany(results -> Flux.fromIterable(results.articles));
	}

}
