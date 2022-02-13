/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.top;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.legacy.anime.*;
import net.sandrohc.jikan.model.legacy.enums.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AnimeTopQuery extends AdvancedTopQuery<AnimeTopQuery, AnimeTopSub, AnimeSubType> {

	public AnimeTopQuery(Jikan jikan, int page) throws JikanInvalidArgumentException {
		super(jikan, Type.ANIME, page);
	}

	@Override
	public Class<AnimeTopSub> getRequestClass() {
		return AnimeTopSub.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return AnimeTop.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<AnimeTopSub> process(Mono<?> content) {
		return ((Mono<AnimeTop>) content).flatMapMany(results -> Flux.fromIterable(results.top));
	}

}
