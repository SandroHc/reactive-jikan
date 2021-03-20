/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;

/**
 * A user favourite.
 */
public class UserFavoritesSub extends MalEntity {

    public String url;

    @JsonProperty("image_url")
    public String imageUrl;

    public String name;


    @Override
    public String toString() {
        return "UserFavoritesSub[" +
                "id=" + malId +
                ", name='" + name + '\'' +
                "]";
    }

}
