/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the anime pictures.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimePictures">Jikan API docs - getAnimePictures</a>
 */
public class AnimePicturesQuery extends Query<DataListHolder<Images>, Flux<Images>> {

	/** The anime ID. */
	protected final int id;

	public AnimePicturesQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/anime/" + id + "/pictures");
	}

	@Override
	public Flux<Images> process(Mono<DataListHolder<Images>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
