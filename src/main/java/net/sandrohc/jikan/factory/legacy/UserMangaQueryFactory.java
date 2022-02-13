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
 * Factory for the user manga queries.
 */
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
     * @return the list of updates
     */
    public UserHistoryMangaQuery updates() {
        return new UserHistoryMangaQuery(this.jikan, username);
    }

}
