/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.model.anime.AnimeEpisodes;
import net.sandrohc.jikan.query.Query;

public class AnimeEpisodesQuery extends Query<AnimeEpisodes> {

	/** The anime ID. */
	private final int id;

	/** The page. Each page contains up to 100 episodes. */
	private final int page;


	public AnimeEpisodesQuery(int id, int page) {
		this.id = id;
		this.page = page;
	}

	@Override
	public String getBaseUri() {
		return "anime/" + id + "/episodes/" + page;
	}

	@Override
	public Class<AnimeEpisodes> getRequestClass() {
		return AnimeEpisodes.class;
	}

}
