/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.query.random.*;

/**
 * Factory for the random queries.
 *
 * @see <a href="https://docs.api.jikan.moe/#tag/random">Jikan API docs - random</a>
 */
public class RandomQueryFactory extends Factory {

    public RandomQueryFactory(Jikan jikan) {
        super(jikan);
    }

    /**
     * Get a random anime.
     *
     * @return The random anime query
     * @see <a href="https://docs.api.jikan.moe/#operation/getRandomAnime">Jikan API docs - getRandomAnime</a>
     */
    public RandomAnimeQuery anime() {
        return new RandomAnimeQuery(this.jikan);
    }

    /**
     * Get a random manga.
     *
     * @return The random manga query
     * @see <a href="https://docs.api.jikan.moe/#operation/getRandomManga">Jikan API docs - getRandomManga</a>
     */
    public RandomMangaQuery manga() {
        return new RandomMangaQuery(this.jikan);
    }

    /**
     * Get a random character.
     *
     * @return The random character query
     * @see <a href="https://docs.api.jikan.moe/#operation/getRandomCharacter">Jikan API docs - getRandomCharacter</a>
     */
    public RandomCharacterQuery character() {
        return new RandomCharacterQuery(this.jikan);
    }

    /**
     * Get a random person.
     *
     * @return The random person query
     * @see <a href="https://docs.api.jikan.moe/#operation/getRandomPerson">Jikan API docs - getRandomPerson</a>
     */
    public RandomPersonQuery person() {
        return new RandomPersonQuery(this.jikan);
    }

    /**
     * Get a random user.
     *
     * @return The random user query
     * @see <a href="https://docs.api.jikan.moe/#operation/getRandomUser">Jikan API docs - getRandomUser</a>
     */
    public RandomUserQuery user() {
        return new RandomUserQuery(this.jikan);
    }
}
