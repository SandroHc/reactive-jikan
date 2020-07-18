/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.top;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.enums.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AnimeTopQuery extends AdvancedTopQuery<AnimeTopQuery, AnimeTop, AnimeTopSub, AnimeSubType> {

	public AnimeTopQuery(Jikan jikan, int page) throws JikanInvalidArgumentException {
		super(jikan, Type.ANIME, page);
	}

	@Override
	public Class<AnimeTop> getRequestClass() {
		return AnimeTop.class;
	}

	@Override
	public Flux<AnimeTopSub> process(Mono<AnimeTop> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.top));
	}

}
