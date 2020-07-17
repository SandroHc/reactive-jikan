/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.QueryMono;

public class MangaReviewsQuery extends QueryMono<Reviews> {

	/** The manga ID. */
	private final int id;

	/** The page. Each page contains up to 20 reviews. */
	private final int page;

	public MangaReviewsQuery(Jikan jikan, int id, int page) {
		super(jikan);
		this.id = id;
		this.page = page;
	}

	@Override
	public String getUri() {
		return "/manga/" + id + "/reviews/" + page;
	}

	@Override
	public Class<Reviews> getRequestClass() {
		return Reviews.class;
	}

}
