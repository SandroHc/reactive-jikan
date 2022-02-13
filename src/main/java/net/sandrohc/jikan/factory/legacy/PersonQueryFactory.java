/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory.legacy;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.factory.Factory;
import net.sandrohc.jikan.query.legacy.person.PersonPicturesQuery;
import net.sandrohc.jikan.query.legacy.person.PersonQuery;
import net.sandrohc.jikan.query.legacy.search.PersonSearchQuery;
import net.sandrohc.jikan.query.legacy.top.PersonTopQuery;

/**
 * Factory for the person queries.
 */
public class PersonQueryFactory extends Factory {

    public PersonQueryFactory(Jikan jikan) {
        super(jikan);
    }

    public PersonQuery get(int id) {
        return new PersonQuery(this.jikan, id);
    }

    public PersonSearchQuery search() {
        return new PersonSearchQuery(this.jikan);
    }

    public PersonTopQuery top(int page) throws JikanInvalidArgumentException {
        return new PersonTopQuery(this.jikan, page);
    }

    public PersonPicturesQuery pictures(int id) {
        return new PersonPicturesQuery(this.jikan, id);
    }

}
