/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.model.anime.AnimeCharactersAndStaff;
import net.sandrohc.jikan.query.Query;

public class AnimeCharactersAndStaffQuery extends Query<AnimeCharactersAndStaff> {

	private final int id;


	public AnimeCharactersAndStaffQuery(int id) {
		this.id = id;
	}

	@Override
	public String getBaseUri() {
		return "anime/" + id + "/characters_staff";
	}

	@Override
	public Class<AnimeCharactersAndStaff> getRequestClass() {
		return AnimeCharactersAndStaff.class;
	}

}
