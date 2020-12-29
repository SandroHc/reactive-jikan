/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * The favorites of a MAL user.
 */
public class UserFavorites implements Serializable {

	public List<UserFavoritesSub> anime = Collections.emptyList();

	public List<UserFavoritesSub> manga = Collections.emptyList();

	public List<UserFavoritesSub> characters = Collections.emptyList();

	public List<UserFavoritesSub> people = Collections.emptyList();


	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserFavorites that = (UserFavorites) o;

		return Objects.equals(anime, that.anime)
			&& Objects.equals(manga, that.manga)
			&& Objects.equals(characters, that.characters)
			&& Objects.equals(people, that.people);
	}

	@Generated
	@Override
	public int hashCode() {
		int result = anime != null ? anime.hashCode() : 0;
		result = 31 * result + (manga != null ? manga.hashCode() : 0);
		result = 31 * result + (characters != null ? characters.hashCode() : 0);
		result = 31 * result + (people != null ? people.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "UserFavorites[" +
				"anime=" + anime.size() +
				", manga=" + manga.size() +
				", characters=" + characters.size() +
				", people=" + people.size() +
				']';
	}

}
