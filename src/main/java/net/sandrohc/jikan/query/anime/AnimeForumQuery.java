/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.common.Forum;
import net.sandrohc.jikan.query.Query;

public class AnimeForumQuery extends Query<Forum> {

	/** The anime ID. */
	private final int id;

	public AnimeForumQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUri() {
		return "/anime/" + id + "/forum";
	}

	@Override
	public Class<Forum> getRequestClass() {
		return Forum.class;
	}

}
