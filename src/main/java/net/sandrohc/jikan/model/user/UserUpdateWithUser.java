/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import net.sandrohc.jikan.utils.Generated;

/**
 * A user update to an anime/manga entry.
 */
public class UserUpdateWithUser extends UserUpdate {

    /** The user details. */
    public UserSimple user;


    public UserSimple getUser() {
        return user;
    }

    public void setUser(UserSimple user) {
        this.user = user;
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        UserUpdateWithUser that = (UserUpdateWithUser) o;

        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Generated
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Generated
    @Override
    public String toString() {
        return "UserUpdateWithUser[user='" + user + "', score=" + score + ", date=" + date + ']';
    }
}
