/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import java.util.*;

import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * The list of anime details of a MAL user.
 */
public class UserAnimeList extends CacheEntity {

	public List<UserAnime> anime = Collections.emptyList();


	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserAnimeList that = (UserAnimeList) o;

		return Objects.equals(anime, that.anime);
	}

	@Generated
	@Override
	public int hashCode() {
		return anime != null ? anime.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "UserAnimeList[" +
				"anime=[" + anime + ']' +
				']';
	}

}
