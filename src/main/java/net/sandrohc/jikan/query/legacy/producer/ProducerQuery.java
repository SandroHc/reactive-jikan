/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.producer;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.legacy.producer.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Mono;

public class ProducerQuery extends Query<Producer, Mono<Producer>> {

	/** The producer, studio, or licensor ID. */
	protected final int id;

	/** The page. Each page contains up to 100 anime. */
	protected final int page;

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
