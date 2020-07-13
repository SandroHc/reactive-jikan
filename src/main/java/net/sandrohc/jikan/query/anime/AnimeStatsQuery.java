/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.model.anime.AnimePictures;
import net.sandrohc.jikan.model.anime.AnimeStats;
import net.sandrohc.jikan.query.Query;

public class AnimeStatsQuery extends Query<AnimeStats> {

	private final int id;


	public AnimeStatsQuery(int id) {
		this.id = id;
	}

	@Override
	public String getBaseUri() {
		return "anime/" + id + "/stats";
	}

	@Override
	public Class<AnimeStats> getRequestClass() {
		return AnimeStats.class;
	}

}
