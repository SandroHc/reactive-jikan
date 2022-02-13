/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.season;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.legacy.enums.*;
import net.sandrohc.jikan.model.legacy.season.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class SeasonQuery extends Query<SeasonAnime, Flux<SeasonAnime>> {

	/** The season year. */
	private final int year;

	/** The season. */
	private final Season season;

	public SeasonQuery(Jikan jikan, int year, Season season) {
		super(jikan);
		this.year = year;
		this.season = season;
	}

	@Override
	public String getUri() {
		return "/season/" + year + "/" + season.name().toLowerCase();
	}

	@Override
	public Class<SeasonAnime> getRequestClass() {
		return SeasonAnime.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return SeasonList.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<SeasonAnime> process(Mono<?> content) {
		return ((Mono<SeasonList>) content).flatMapMany(results -> Flux.fromIterable(results.anime));
	}

}
