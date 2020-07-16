/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;

public class MangaNewsQuery extends Query<News> {

	/** The manga ID. */
	private final int id;

	public MangaNewsQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUri() {
		return "/manga/" + id + "/news";
	}

	@Override
	public Class<News> getRequestClass() {
		return News.class;
	}

}
