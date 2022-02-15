/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import java.io.*;

import net.sandrohc.jikan.utils.Generated;

/**
 * A user about page. Supports HTML.
 */
public class UserAbout implements Serializable {

    /** The about page. Supports HTML. */
    public String about;


    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAbout userAbout = (UserAbout) o;

        return about != null ? about.equals(userAbout.about) : userAbout.about == null;
    }

    @Generated
    @Override
    public int hashCode() {
        return about != null ? about.hashCode() : 0;
    }

    @Generated
    @Override
    public String toString() {
        return "UserAbout[about='" + about + "']";
    }
}
