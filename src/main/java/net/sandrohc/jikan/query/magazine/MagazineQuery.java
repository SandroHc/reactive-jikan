/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.magazine;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.magazine.*;
import net.sandrohc.jikan.query.QueryMono;

public class MagazineQuery extends QueryMono<Magazine> {

	/** The magazine, serializer, or publisher ID. */
	private final int id;

	/** The page. Each page contains up to 100 manga. */
	public final int page;

	public MagazineQuery(Jikan jikan, int id, int page) throws JikanInvalidArgumentException {
		super(jikan);
		if (page < 1) throw new JikanInvalidArgumentException("page starts at index 1");

		this.id = id;
		this.page = page;
	}

	@Override
	public String getUri() {
		return "/magazine/" + id + '/' + page;
	}

	@Override
	public Class<Magazine> getRequestClass() {
		return Magazine.class;
	}

}
