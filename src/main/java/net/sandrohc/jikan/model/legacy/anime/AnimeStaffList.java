/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.anime;

import java.util.*;

import net.sandrohc.jikan.model.legacy.base.*;

/**
 * The base object for the anime staff query.
 */
public class AnimeStaffList extends CacheEntity {

	public List<AnimeStaff> data = Collections.emptyList();


	@Override
	public String toString() {
		return "AnimeStaffList[staff=" + data.size() + " staff]";
	}

}
