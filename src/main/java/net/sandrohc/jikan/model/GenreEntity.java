/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model;

import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.enums.*;

/**
 * The base object for anime/manga genre with a type, name and URL.
 *
 * @param <T> the type of genre, either {@linkplain AnimeGenre} or {@linkplain MangaGenre}
 */
public class GenreEntity<T> extends MalEntity {

	/** The genre type. */
	public Type type;

	/** The genre name. */
	public T name;

	/** The URL to the genre page on MyAnimeList. */
	public String url;

	@Override
	public String toString() {
		return "Genre[id=" + malId + ", genre=" + name + ']';
	}

}
