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
 * The list of manga details of a MAL user.
 */
public class UserMangaList extends CacheEntity {

	public List<UserManga> manga = Collections.emptyList();


	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserMangaList that = (UserMangaList) o;

		return Objects.equals(manga, that.manga);
	}

	@Generated
	@Override
	public int hashCode() {
		return manga != null ? manga.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "UserMangaList[" +
				"manga=[" + manga + ']' +
				']';
	}

}
