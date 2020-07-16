/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.manga;

import java.util.*;

import net.sandrohc.jikan.model.base.*;

public class MangaCharacters extends CacheEntity {

	public List<RoleSubEntity> characters = Collections.emptyList();


	@Override
	public String toString() {
		return "MangaCharacters[characters=" + (characters.size() + " characters") + ']';
	}

}
