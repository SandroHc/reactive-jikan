/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.model.anime.AnimeMoreInfo;
import net.sandrohc.jikan.model.anime.AnimeNews;
import net.sandrohc.jikan.query.Query;

public class AnimeMoreInfoQuery extends Query<AnimeMoreInfo> {

	private final int id;


	public AnimeMoreInfoQuery(int id) {
		this.id = id;
	}

	@Override
	public String getBaseUri() {
		return "anime/" + id + "/moreinfo";
	}

	@Override
	public Class<AnimeMoreInfo> getRequestClass() {
		return AnimeMoreInfo.class;
	}

}
