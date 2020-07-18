/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.top;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.manga.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MangaTopQuery extends AdvancedTopQuery<MangaTopQuery, MangaTop, MangaTopSub, MangaSubType> {

	public MangaTopQuery(Jikan jikan, int page) throws JikanInvalidArgumentException {
		super(jikan, Type.MANGA, page);
	}

	@Override
	public Class<MangaTop> getRequestClass() {
		return MangaTop.class;
	}

	@Override
	public Flux<MangaTopSub> process(Mono<MangaTop> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.top));
	}

}
