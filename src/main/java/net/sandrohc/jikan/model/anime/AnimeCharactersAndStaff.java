/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.util.Collections;
import java.util.List;

import net.sandrohc.jikan.model.base.*;

public class AnimeCharactersAndStaff extends CacheEntity {

	public List<AnimeCharacter> characters = Collections.emptyList();

	public List<AnimeStaff> staff = Collections.emptyList();


	@Override
	public String toString() {
		return "AnimeCharactersAndStaff[characters=" + characters.size() + " characters, staff=" + staff.size() + " staff]";
	}

}
