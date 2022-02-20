/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.query.person.*;

/**
 * Factory for the people queries.
 *
 * @see <a href="https://docs.api.jikan.moe/#tag/people">Jikan API docs - people</a>
 */
public class PersonQueryFactory extends Factory {

    public PersonQueryFactory(Jikan jikan) {
        super(jikan);
    }

    /**
     * Get the person details.
     *
     * @param personId The person ID
     * @return The person query
     * @see <a href="https://docs.api.jikan.moe/#operation/getPersonById">Jikan API docs - getPersonById</a>
     */
    public PersonQuery get(int personId) {
        return new PersonQuery(this.jikan, personId);
    }

    /**
     * Get the person search.
     *
     * @return The person search query
     * @see <a href="https://docs.api.jikan.moe/#operation/getPersonById">Jikan API docs - getPersonById</a>
     */
    public PersonSearchQuery search() {
        return new PersonSearchQuery(this.jikan);
    }

    /**
     * Get the most popular person by number of favorites.
     *
     * @return The person top query
     * @see <a href="https://docs.api.jikan.moe/#operation/getTopPeople">Jikan API docs - getTopPeople</a>
     */
    public PersonTopQuery top() {
        return new PersonTopQuery(this.jikan);
    }

    /**
     * Get the person anime-related positions.
     *
     * @param personId The person ID
     * @return The person anime query
     * @see <a href="https://docs.api.jikan.moe/#operation/getPersonAnime">Jikan API docs - getPersonAnime</a>
     */
    public PersonAnimeQuery anime(int personId) {
        return new PersonAnimeQuery(this.jikan, personId);
    }

    /**
     * Get the person voice-actor positions.
     *
     * @param personId The person ID
     * @return The person voices query
     * @see <a href="https://docs.api.jikan.moe/#operation/getPersonVoices">Jikan API docs - getPersonVoices</a>
     */
    public PersonVoicesQuery voices(int personId) {
        return new PersonVoicesQuery(this.jikan, personId);
    }

    /**
     * Get the person manga-related positions.
     *
     * @param personId The person ID
     * @return The person manga query
     * @see <a href="https://docs.api.jikan.moe/#operation/getPersonManga">Jikan API docs - getPersonManga</a>
     */
    public PersonMangaQuery manga(int personId) {
        return new PersonMangaQuery(this.jikan, personId);
    }

    /**
     * Get the person pictures.
     *
     * @param personId The person ID
     * @return The person pictures query
     * @see <a href="https://docs.api.jikan.moe/#operation/getPersonPictures">Jikan API docs - getPersonPictures</a>
     */
    public PersonPicturesQuery pictures(int personId) {
        return new PersonPicturesQuery(this.jikan, personId);
    }
}
