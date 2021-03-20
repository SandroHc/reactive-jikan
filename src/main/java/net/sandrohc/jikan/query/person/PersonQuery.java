/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.person;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.person.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Mono;

public class PersonQuery extends Query<Person, Mono<Person>> {

	private final int id;

	public PersonQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUri() {
		return "/person/" + id;
	}

	@Override
	public Class<Person> getRequestClass() {
		return Person.class;
	}

}
