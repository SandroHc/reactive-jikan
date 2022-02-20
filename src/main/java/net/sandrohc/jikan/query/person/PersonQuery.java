/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.person;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.person.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the person details.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getPersonById">Jikan API docs - getPersonById</a>
 */
public class PersonQuery extends Query<DataHolder<Person>, Mono<Person>> {

	/** The person ID. */
	protected final int id;

	public PersonQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/people/" + id);
	}

	@Override
	public Mono<Person> process(Mono<DataHolder<Person>> content) {
		return content.map(holder -> holder.data);
	}
}
