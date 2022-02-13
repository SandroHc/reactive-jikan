/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory.legacy;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.factory.Factory;
import net.sandrohc.jikan.query.legacy.club.ClubMembersQuery;
import net.sandrohc.jikan.query.legacy.club.ClubQuery;

/**
 * Factory for the club queries.
 */
public class ClubQueryFactory extends Factory {

    /**
     * The club ID.
     */
    public final int id;


    public ClubQueryFactory(Jikan jikan, int id) {
        super(jikan);
        this.id = id;
    }

    public ClubQuery get() {
        return new ClubQuery(this.jikan, id);
    }

    public ClubMembersQuery members(int page) {
        return new ClubMembersQuery(this.jikan, id, page);
    }

}
