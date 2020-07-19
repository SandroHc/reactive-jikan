/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.producer;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.producer.*;
import net.sandrohc.jikan.query.QueryMono;

public class ProducerQuery extends QueryMono<Producer> {

	/** The producer, studio, or licensor ID. */
	private final int id;

	/** The page. Each page contains up to 100 anime. */
	public final int page;

	public ProducerQuery(Jikan jikan, int id, int page) throws JikanInvalidArgumentException {
		super(jikan);
		if (page < 1) throw new JikanInvalidArgumentException("page starts at index 1");

		this.id = id;
		this.page = page;
	}

	@Override
	public String getUri() {
		return "/producer/" + id + '/' + page;
	}

	@Override
	public Class<Producer> getRequestClass() {
		return Producer.class;
	}

}
