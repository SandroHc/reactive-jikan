/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import java.io.*;

import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * Base details of a user.
 */
public class UserSimple implements Serializable {

	/** The user name. */
	public String username;

	/** URL to the user's profile. */
	public String url;

	/** The user avatar */
	public Images images;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserSimple that = (UserSimple) o;

		return username != null ? username.equals(that.username) : that.username == null;
	}

	@Generated
	@Override
	public int hashCode() {
		return username != null ? username.hashCode() : 0;
	}

	@Generated
	@Override
	public String toString() {
		return "UserSimple[username='" + username + "']";
	}
}
