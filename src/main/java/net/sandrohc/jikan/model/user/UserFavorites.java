/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import java.io.*;
import java.util.*;

import net.sandrohc.jikan.utils.Generated;

/**
 * The list of favorites of a MAL user.
 */
public class UserFavorites implements Serializable {

	/** The list of favorited anime. */
	public Collection<UserFavoriteAnime> anime = Collections.emptyList();

	/** The list of favorited manga. */
	public Collection<UserFavoriteManga> manga = Collections.emptyList();

	/** The list of favorited characters. */
	public Collection<UserFavoriteCharacter> characters = Collections.emptyList();

	/** The list of favorited people. */
	public Collection<UserFavoritePerson> people = Collections.emptyList();


	public Collection<UserFavoriteAnime> getAnime() {
		return anime;
	}

	public void setAnime(Collection<UserFavoriteAnime> anime) {
		this.anime = anime;
	}

	public Collection<UserFavoriteManga> getManga() {
		return manga;
	}

	public void setManga(Collection<UserFavoriteManga> manga) {
		this.manga = manga;
	}

	public Collection<UserFavoriteCharacter> getCharacters() {
		return characters;
	}

	public void setCharacters(Collection<UserFavoriteCharacter> characters) {
		this.characters = characters;
	}

	public Collection<UserFavoritePerson> getPeople() {
		return people;
	}

	public void setPeople(Collection<UserFavoritePerson> people) {
		this.people = people;
	}

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

	@Generated
	@Override
	public String toString() {
		return "UserFavorites[anime=" + anime.size() + ", manga=" + manga.size() + ", characters=" + characters.size() +
				", people=" + people.size() + ']';
	}
}
