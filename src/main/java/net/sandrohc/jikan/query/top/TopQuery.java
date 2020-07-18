/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.top;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.QueryFlux;

@SuppressWarnings("unchecked")
public abstract class TopQuery<C extends TopQuery<C, TYPE_INITIAL, TYPE_FINAL>, TYPE_INITIAL, TYPE_FINAL> extends QueryFlux<TYPE_INITIAL, TYPE_FINAL> {

	/** The type os results. */
	public final Type type;

	/** The page. Each page contains up to 50 results. */
	public final int page;

	protected TopQuery(Jikan jikan, Type type, int page) throws JikanInvalidArgumentException {
		super(jikan);
		if (page < 1) throw new JikanInvalidArgumentException("page starts at index 1");

		this.type = type;
		this.page = page;
	}

	@Override
	public String getUri() {
		return "/top/" + type.top + '/' + page;
	}

}
