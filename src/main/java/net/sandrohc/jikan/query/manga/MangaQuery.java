/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.manga.*;
import net.sandrohc.jikan.query.Query;

public class MangaQuery extends Query<Manga> {

	private final int id;

	public MangaQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUri() {
		return "/manga/" + id;
	}

	@Override
	public Class<Manga> getRequestClass() {
		return Manga.class;
	}

}
