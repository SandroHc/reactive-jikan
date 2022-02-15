/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import java.io.*;
import java.time.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.utils.Generated;

/**
 * A user friend.
 */
public class UserFriend implements Serializable {

	/** The friend details. */
	public UserSimple user;

	/** The last time this user was seen online. */
	@JsonProperty("last_online")
	public OffsetDateTime lastOnline;

	/** The date this user is friends with the requested user. */
	@JsonProperty("friends_since")
	public OffsetDateTime friendsSince;


	public UserSimple getUser() {
		return user;
	}

	public void setUser(UserSimple user) {
		this.user = user;
	}

	public OffsetDateTime getLastOnline() {
		return lastOnline;
	}

	public void setLastOnline(OffsetDateTime lastOnline) {
		this.lastOnline = lastOnline;
	}

	public OffsetDateTime getFriendsSince() {
		return friendsSince;
	}

	public void setFriendsSince(OffsetDateTime friendsSince) {
		this.friendsSince = friendsSince;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserFriend that = (UserFriend) o;

		if (user != null ? !user.equals(that.user) : that.user != null) return false;
		if (lastOnline != null ? !lastOnline.equals(that.lastOnline) : that.lastOnline != null) return false;
		return friendsSince != null ? friendsSince.equals(that.friendsSince) : that.friendsSince == null;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = user != null ? user.hashCode() : 0;
		result = 31 * result + (lastOnline != null ? lastOnline.hashCode() : 0);
		result = 31 * result + (friendsSince != null ? friendsSince.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "UserFriend[user=" + user + ", lastOnline=" + lastOnline + ", friendsSince=" + friendsSince + ']';
	}
}
