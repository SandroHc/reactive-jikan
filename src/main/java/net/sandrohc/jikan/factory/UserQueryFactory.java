/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.query.user.*;

public class UserQueryFactory extends Factory {

    public final String username;


    public UserQueryFactory(Jikan jikan, String username) {
        super(jikan);
        this.username = username;
    }

    public UserProfileQuery profile() {
        return new UserProfileQuery(this.jikan, username);
    }

    public UserAnimeQueryFactory anime() {
        return new UserAnimeQueryFactory(this.jikan, username);
    }

    public UserMangaQueryFactory manga() {
        return new UserMangaQueryFactory(this.jikan, username);
    }

    public UserFriendsQuery friends(int page) {
        return new UserFriendsQuery(this.jikan, username, page);
    }

}
