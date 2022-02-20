/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.query.character.*;

/**
 * Factory for the character queries.
 *
 * @see <a href="https://docs.api.jikan.moe/#tag/characters">Jikan API docs - characters</a>
 */
public class CharacterQueryFactory extends Factory {

	public CharacterQueryFactory(Jikan jikan) {
		super(jikan);
	}

	/**
	 * Get the character details.
	 *
	 * @param characterId The character ID
	 * @return The character query
	 * @see <a href="https://docs.api.jikan.moe/#operation/getCharacterById">Jikan API docs - getCharacterById</a>
	 */
	public CharacterQuery get(int characterId) {
		return new CharacterQuery(this.jikan, characterId);
	}

	/**
	 * Get the character search.
	 *
	 * @return The character search query
	 * @see <a href="https://docs.api.jikan.moe/#operation/getCharactersSearch">Jikan API docs - getCharactersSearch</a>
	 */
    public CharacterSearchQuery search() {
        return new CharacterSearchQuery(this.jikan);
    }

	/**
	 * Get the most popular character by number of favorites.
	 *
	 * @return The character top query
	 * @see <a href="https://docs.api.jikan.moe/#operation/getTopCharacters">Jikan API docs - getTopCharacters</a>
	 */
	public CharacterTopQuery top() {
		return new CharacterTopQuery(this.jikan);
	}

	/**
	 * Get the character appearances in anime.
	 *
	 * @return The character anime query
	 * @see <a href="https://docs.api.jikan.moe/#operation/getCharacterAnime">Jikan API docs - getCharacterAnime</a>
	 */
	public CharacterAnimeQuery anime(int characterId) {
		return new CharacterAnimeQuery(this.jikan, characterId);
	}

	/**
	 * Get the character appearances in manga.
	 *
	 * @return The character manga query
	 * @see <a href="https://docs.api.jikan.moe/#operation/getCharacterManga">Jikan API docs - getCharacterManga</a>
	 */
	public CharacterMangaQuery manga(int characterId) {
		return new CharacterMangaQuery(this.jikan, characterId);
	}

	/**
	 * Get the character voice actors.
	 *
	 * @return The character voice actors query
	 * @see <a href="https://docs.api.jikan.moe/#operation/getCharacterVoiceActors">Jikan API docs - getCharacterVoiceActors</a>
	 */
	public CharacterVoiceActorsQuery voiceActors(int characterId) {
		return new CharacterVoiceActorsQuery(this.jikan, characterId);
	}

	/**
	 * Get the character pictures.
	 *
	 * @return The character pictures query
	 * @see <a href="https://docs.api.jikan.moe/#operation/getCharacterPictures">Jikan API docs - getCharacterPictures</a>
	 */
	public CharacterPicturesQuery pictures(int characterId) {
		return new CharacterPicturesQuery(this.jikan, characterId);
	}
}
