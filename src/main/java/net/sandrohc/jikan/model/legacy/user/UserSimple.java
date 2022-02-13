/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.user;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.utils.Generated;

/**
 * Base details of a user.
 */
public class UserSimple implements Serializable {

	/** The user name. */
	public String username;

	/** The URL to the user's profile on MyAnimeList. */
	public String url;

	@JsonProperty("image_url")
	public String imageUrl;


	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserSimple that = (UserSimple) o;

		return Objects.equals(username, that.username);
	}

	@Generated
	@Override
	public int hashCode() {
		return username != null ? username.hashCode() : 0;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[username='" + username + "']";
	}

}
