/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.query.user.*;

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
