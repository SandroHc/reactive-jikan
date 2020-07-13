/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.search;

import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.Query;

@SuppressWarnings("unchecked")
public abstract class SearchQuery<C extends SearchQuery<C,R>, R> implements Query<R> {

	protected final Type type;


	protected SearchQuery(Type type) {
		this.type = type;
	}

	public C page(int page) {
		queryParams.put("page", page);
		return (C) this;
	}

	@Override
	public String getBaseUri() {
		return "search/" + type.name().toLowerCase();
	}

}
