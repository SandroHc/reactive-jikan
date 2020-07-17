/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.QueryFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AnimePicturesQuery extends QueryFlux<Pictures, Picture> {

	/** The anime ID. */
	private final int id;

	public AnimePicturesQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUri() {
		return "/anime/" + id + "/pictures";
	}

	@Override
	public Class<Pictures> getRequestClass() {
		return Pictures.class;
	}

	@Override
	public Flux<Picture> process(Mono<Pictures> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.pictures));
	}
}
