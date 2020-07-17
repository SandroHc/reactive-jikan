/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.season;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.season.*;
import net.sandrohc.jikan.query.QueryFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class SeasonLaterQuery extends QueryFlux<SeasonList, SeasonAnime> {

	public SeasonLaterQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public String getUri() {
		return "/season/later";
	}

	@Override
	public Class<SeasonList> getRequestClass() {
		return SeasonList.class;
	}

	@Override
	public Flux<SeasonAnime> process(Mono<SeasonList> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.anime));
	}

}
