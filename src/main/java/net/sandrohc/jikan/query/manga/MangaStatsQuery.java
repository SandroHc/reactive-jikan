/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Mono;

public class MangaStatsQuery extends Query<Stats, Mono<Stats>> {

	/** The manga ID. */
	private final int id;

	public MangaStatsQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUri() {
		return "/manga/" + id + "/stats";
	}

	@Override
	public Class<Stats> getRequestClass() {
		return Stats.class;
	}

}
