/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.query.Query;

public class AnimeVideosQuery extends Query<AnimeVideos> {

	private final int id;

	public AnimeVideosQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getBaseUri() {
		return "/anime/" + id + "/videos";
	}

	@Override
	public Class<AnimeVideos> getRequestClass() {
		return AnimeVideos.class;
	}

}
