/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.query.QueryMono;

public class AnimeQuery extends QueryMono<Anime> {

	/** The anime ID. */
	private final int id;

	public AnimeQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUri() {
		return "/anime/" + id;
	}

	@Override
	public Class<Anime> getRequestClass() {
		return Anime.class;
	}

}
