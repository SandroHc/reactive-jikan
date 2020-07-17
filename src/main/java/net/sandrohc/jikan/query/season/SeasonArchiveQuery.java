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

public class SeasonArchiveQuery extends QueryFlux<SeasonArchive, SeasonArchiveYear> {

	public SeasonArchiveQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public String getUri() {
		return "/season/archive";
	}

	@Override
	public Class<SeasonArchive> getRequestClass() {
		return SeasonArchive.class;
	}

	@Override
	public Flux<SeasonArchiveYear> process(Mono<SeasonArchive> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.archive));
	}

}
