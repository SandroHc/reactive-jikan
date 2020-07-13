/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.search;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.search.*;

public class PersonSearchQuery extends SearchQuery<PersonSearchQuery, PersonSearch> {

	public PersonSearchQuery(Jikan jikan) {
		super(jikan, Type.PERSON);
	}

	@Override
	public Class<PersonSearch> getRequestClass() {
		return PersonSearch.class;
	}
}
