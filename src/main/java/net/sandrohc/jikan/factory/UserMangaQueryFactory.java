/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.query.user.*;

public class UserMangaQueryFactory extends Factory {

    public final String username;

    public UserMangaQueryFactory(Jikan jikan, String username) {
        super(jikan);
        this.username = username;
    }

    public UserMangaQuery list(int page) {
        return new UserMangaQuery(this.jikan, username, page);
    }

    /**
     * Returns the latest updates to the user's manga entries.
     *
     * @param username the user name
     * @return the list of updates
     */
    public UserHistoryMangaQuery updates() {
        return new UserHistoryMangaQuery(this.jikan, username);
    }

}
