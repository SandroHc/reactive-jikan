/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Mono;

public class AnimeCharactersAndStaffQuery extends Query<AnimeCharactersAndStaff, Mono<AnimeCharactersAndStaff>> {

	/** The anime ID. */
	private final int id;

	public AnimeCharactersAndStaffQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUri() {
		return "/anime/" + id + "/characters_staff";
	}

	@Override
	public Class<AnimeCharactersAndStaff> getRequestClass() {
		return AnimeCharactersAndStaff.class;
	}

}
