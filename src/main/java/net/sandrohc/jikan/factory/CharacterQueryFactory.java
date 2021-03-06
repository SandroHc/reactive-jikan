/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.query.character.CharacterPicturesQuery;
import net.sandrohc.jikan.query.character.CharacterQuery;
import net.sandrohc.jikan.query.search.CharacterSearchQuery;
import net.sandrohc.jikan.query.top.CharacterTopQuery;

/**
 * Factory for the character queries.
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

    public CharacterTopQuery top(int page) throws JikanInvalidArgumentException {
        return new CharacterTopQuery(this.jikan, page);
    }

    public CharacterPicturesQuery pictures(int id) {
        return new CharacterPicturesQuery(this.jikan, id);
    }

}
