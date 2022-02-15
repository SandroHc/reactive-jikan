/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import java.time.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;

/**
 * User object.
 */
public class User extends MalEntity {

	/** The user name. */
	public String username;

	/** URL to the user's profile. */
	public String url;

	/** The user avatar */
	public Images images;

	/** The last time the user was online. */
	@JsonProperty("last_online")
	public OffsetDateTime lastOnline;

	/** The user gender. */
	public UserGender gender;

	/** The user birthday. Will be {@code null} if unknown. */
	public OffsetDateTime birthday;

	/** The user location. */
	public String location;

	/** The user join date. */
	public OffsetDateTime joined;


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

	public OffsetDateTime getLastOnline() {
		return lastOnline;
	}

	public void setLastOnline(OffsetDateTime lastOnline) {
		this.lastOnline = lastOnline;
	}

	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	public OffsetDateTime getBirthday() {
		return birthday;
	}

	public void setBirthday(OffsetDateTime birthday) {
		this.birthday = birthday;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public OffsetDateTime getJoined() {
		return joined;
	}

	public void setJoined(OffsetDateTime joined) {
		this.joined = joined;
	}

	@Override
	public String toString() {
		return "User[id=" + malId + ", username='" + username + "']";
	}
}
