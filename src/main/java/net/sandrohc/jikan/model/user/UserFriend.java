/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import java.io.*;
import java.time.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.utils.Generated;

/**
 * The friend of a MAL user.
 */
public class UserFriend implements Serializable {

	public String username;

	public String url;

	@JsonProperty("image_url")
	public String imageUrl;

	@JsonProperty("last_online")
	public OffsetDateTime lastOnline;

	@JsonProperty("friends_since")
	public OffsetDateTime friendsSince;


	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserFriend that = (UserFriend) o;

		return Objects.equals(username, that.username);
	}

	@Generated
	@Override
	public int hashCode() {
		return username != null ? username.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "UserFriend[" +
				"username='" + username + '\'' +
				']';
	}
}
