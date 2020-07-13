/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.search;

import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.search.*;

public class CharacterSearchQuery extends SearchQuery<CharacterSearchQuery, CharacterSearch> {

	public CharacterSearchQuery() {
		super(Type.CHARACTER);
	}

	@Override
	public Class<CharacterSearch> getRequestClass() {
		return CharacterSearch.class;
	}

}
