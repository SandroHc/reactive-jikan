/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.club;

import java.util.*;

import net.sandrohc.jikan.model.*;

/**
 * A list of club relations.
 */
public class ClubRelations extends MalEntity {

	/** The anime relations. */
	public List<EntityWithType> anime = Collections.emptyList();

	/** The manga relations. */
	public List<EntityWithType> manga = Collections.emptyList();

	/** The characters relations. */
	public List<EntityWithType> characters = Collections.emptyList();


	public List<EntityWithType> getAnime() {
		return anime;
	}

	public void setAnime(List<EntityWithType> anime) {
		this.anime = anime;
	}

	public List<EntityWithType> getManga() {
		return manga;
	}

	public void setManga(List<EntityWithType> manga) {
		this.manga = manga;
	}

	public List<EntityWithType> getCharacters() {
		return characters;
	}

	public void setCharacters(List<EntityWithType> characters) {
		this.characters = characters;
	}

	@Override
	public String toString() {
		return "ClubRelations[anime=" + (anime != null ? anime.size() : null) +
				", manga=" + (manga != null ? manga.size() : null) +
				", characters=" + (manga != null ? manga.size() : null) +
				"']";
	}
}
