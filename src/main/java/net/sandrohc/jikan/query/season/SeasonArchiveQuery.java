/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.season;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.season.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class SeasonArchiveQuery extends Query<SeasonArchiveYear, Flux<SeasonArchiveYear>> {

	public SeasonArchiveQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public String getUri() {
		return "/season/archive";
	}

	@Override
	public Class<SeasonArchiveYear> getRequestClass() {
		return SeasonArchiveYear.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return SeasonArchive.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<SeasonArchiveYear> process(Mono<?> content) {
		return ((Mono<SeasonArchive>) content).flatMapMany(results -> Flux.fromIterable(results.archive));
	}

}
