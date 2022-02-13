/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.manga;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.legacy.common.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MangaReviewsQuery extends Query<Review, Flux<Review>> {

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
	public Class<Review> getRequestClass() {
		return Review.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return Reviews.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<Review> process(Mono<?> content) {
		return ((Mono<Reviews>) content).flatMapMany(results -> Flux.fromIterable(results.reviews));
	}
}
