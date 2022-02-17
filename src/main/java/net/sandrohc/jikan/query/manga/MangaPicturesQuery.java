/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the manga pictures.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMangaPictures">Jikan API docs - getMangaPictures</a>
 */
public class MangaPicturesQuery extends Query<DataListHolder<Images>, Flux<Images>> {

	/** The manga ID. */
	private final int id;

	public MangaPicturesQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return endpoint("/manga/" + id + "/pictures");
	}

	@Override
	public TypeReference<DataListHolder<Images>> getResponseType() {
		return new TypeReference<DataListHolder<Images>>() { };
	}

	@Override
	public Flux<Images> process(Mono<DataListHolder<Images>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
