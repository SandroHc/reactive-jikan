/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Mono;

public class AnimeEpisodesQuery extends Query<AnimeEpisodes, Mono<AnimeEpisodes>> {

	/** The anime ID. */
	private final int id;

	/** The page. Each page contains up to 100 episodes. */
	private final int page;

	public AnimeEpisodesQuery(Jikan jikan, int id, int page) throws JikanInvalidArgumentException {
		super(jikan);
		if (page < 1) throw new JikanInvalidArgumentException("page starts at index 1");

		this.id = id;
		this.page = page;
	}

	@Override
	public String getUri() {
		return "/anime/" + id + "/episodes/" + page;
	}

	@Override
	public Class<AnimeEpisodes> getRequestClass() {
		return AnimeEpisodes.class;
	}

}
