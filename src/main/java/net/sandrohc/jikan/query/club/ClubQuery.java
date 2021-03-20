/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.club;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.club.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Mono;

public class ClubQuery extends Query<Club, Mono<Club>> {

	private final int id;

	public ClubQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUri() {
		return "/club/" + id;
	}

	@Override
	public Class<Club> getRequestClass() {
		return Club.class;
	}

}
