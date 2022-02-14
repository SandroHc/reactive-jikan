/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrl.endpoint;

/**
 * Query for the anime reviews.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeReviews">Jikan API docs - getAnimeReviews</a>
 */
public class AnimeReviewsQuery extends Query<DataListHolderWithPagination<Review>, Flux<Review>> {

	/** The anime ID. */
	protected final int id;

	// TODO: validate javadoc by checking if max reviews per page has changed
	/** The page. Each page contains up to 20 reviews. */
	protected final int page;

	public AnimeReviewsQuery(Jikan jikan, int id, int page) throws JikanInvalidArgumentException {
		super(jikan);
		if (page < 1) throw new JikanInvalidArgumentException("page starts at index 1");

		this.id = id;
		this.page = page;
	}

	@Override
	public QueryUrl getUrl() {
		return endpoint("/anime/" + id + "/reviews")
				.param("page", page);
	}

	@Override
	public TypeReference<DataListHolderWithPagination<Review>> getResponseType() {
		return new TypeReference<DataListHolderWithPagination<Review>>() { };
	}

	@Override
	public Flux<Review> process(Mono<DataListHolderWithPagination<Review>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
