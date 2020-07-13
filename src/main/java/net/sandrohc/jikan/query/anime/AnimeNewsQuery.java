/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.model.anime.AnimeCharactersAndStaff;
import net.sandrohc.jikan.model.anime.AnimeNews;
import net.sandrohc.jikan.query.Query;

public class AnimeNewsQuery extends Query<AnimeNews> {

	private final int id;


	public AnimeNewsQuery(int id) {
		this.id = id;
	}

	@Override
	public String getBaseUri() {
		return "anime/" + id + "/news";
	}

	@Override
	public Class<AnimeNews> getRequestClass() {
		return AnimeNews.class;
	}

}
