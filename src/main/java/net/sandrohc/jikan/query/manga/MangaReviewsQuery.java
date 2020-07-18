/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.QueryFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MangaReviewsQuery extends QueryFlux<Reviews, Review> {

	/** The manga ID. */
	private final int id;

	/** The page. Each page contains up to 20 reviews. */
	private final int page;

	public MangaReviewsQuery(Jikan jikan, int id, int page) throws JikanInvalidArgumentException {
		super(jikan);
		if (page < 1) throw new JikanInvalidArgumentException("page starts at index 1");

		this.id = id;
		this.page = page;
	}

	@Override
	public String getUri() {
		return "/manga/" + id + "/reviews/" + page;
	}

	@Override
	public Class<Reviews> getRequestClass() {
		return Reviews.class;
	}

	@Override
	public Flux<Review> process(Mono<Reviews> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.reviews));
	}
}
