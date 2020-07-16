/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;

public class AnimeUserUpdatesQuery extends Query<UserUpdates> {

	/** The manga ID. */
	private final int id;

	/** The page. Each page contains up to 100 user updates. */
	private final int page;

	public AnimeUserUpdatesQuery(Jikan jikan, int id, int page) {
		super(jikan);
		this.id = id;
		this.page = page;
	}

	@Override
	public String getUri() {
		return "/anime/" + id + "/userupdates/" + page;
	}

	@Override
	public Class<UserUpdates> getRequestClass() {
		return UserUpdates.class;
	}

}
