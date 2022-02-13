/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory.legacy;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.factory.Factory;
import net.sandrohc.jikan.query.legacy.user.*;

/**
 * Factory for the user anime queries.
 */
public class UserAnimeQueryFactory extends Factory {

    public final String username;

    public UserAnimeQueryFactory(Jikan jikan, String username) {
        super(jikan);
        this.username = username;
    }

    public UserAnimeQuery list(int page) {
        return new UserAnimeQuery(this.jikan, username, page);
    }

    /**
     * Returns the latest updates to the user's anime entries.
     *
     * @return the list of updates
     */
    public UserHistoryAnimeQuery updates() {
        return new UserHistoryAnimeQuery(this.jikan, username);
    }

}
