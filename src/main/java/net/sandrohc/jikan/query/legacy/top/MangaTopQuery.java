/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.top;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.legacy.enums.*;
import net.sandrohc.jikan.model.legacy.manga.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MangaTopQuery extends AdvancedTopQuery<MangaTopQuery, MangaTopSub, MangaSubType> {

	public MangaTopQuery(Jikan jikan, int page) throws JikanInvalidArgumentException {
		super(jikan, Type.MANGA, page);
	}

	@Override
	public Class<MangaTopSub> getRequestClass() {
		return MangaTopSub.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return MangaTop.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<MangaTopSub> process(Mono<?> content) {
		return ((Mono<MangaTop>) content).flatMapMany(results -> Flux.fromIterable(results.top));
	}

}
