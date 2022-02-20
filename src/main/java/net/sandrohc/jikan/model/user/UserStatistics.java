/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import java.io.*;

import net.sandrohc.jikan.utils.Generated;

/**
 * The user statistics.
 */
public class UserStatistics implements Serializable {

	/** The anime statistics. */
	public UserAnimeStatistics anime;

	/** The manga statistics. */
	public UserMangaStatistics manga;


	public UserAnimeStatistics getAnime() {
		return anime;
	}

	public void setAnime(UserAnimeStatistics anime) {
		this.anime = anime;
	}

	public UserMangaStatistics getManga() {
		return manga;
	}

	public void setManga(UserMangaStatistics manga) {
		this.manga = manga;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserStatistics that = (UserStatistics) o;

		if (anime != null ? !anime.equals(that.anime) : that.anime != null) return false;
		return manga != null ? manga.equals(that.manga) : that.manga == null;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = anime != null ? anime.hashCode() : 0;
		result = 31 * result + (manga != null ? manga.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "UserStatistics[anime=" + anime + ", manga=" + manga + ']';
	}
}
