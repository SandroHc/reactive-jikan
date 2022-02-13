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

	public CharacterQuery get(int id) {
		return new CharacterQuery(this.jikan, id);
	}

    public CharacterSearchQuery search() {
        return new CharacterSearchQuery(this.jikan);
    }

	// TODO: implement
//    public CharacterTopQuery top(int page) throws JikanInvalidArgumentException {
//        return new CharacterTopQuery(this.jikan, page);
//    }

	public CharacterAnimeQuery anime(int id) {
		return new CharacterAnimeQuery(this.jikan, id);
	}

	public CharacterMangaQuery manga(int id) {
		return new CharacterMangaQuery(this.jikan, id);
	}

	public CharacterVoiceActorsQuery voiceActors(int id) {
		return new CharacterVoiceActorsQuery(this.jikan, id);
	}

	public CharacterPicturesQuery pictures(int id) {
		return new CharacterPicturesQuery(this.jikan, id);
	}
}
