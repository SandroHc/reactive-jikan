/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.user;

import java.util.*;

import net.sandrohc.jikan.model.legacy.base.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * The friend of a MAL user.
 */
public class UserFriends extends CacheEntity {

	public List<UserFriend> friends = Collections.emptyList();


	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserFriends that = (UserFriends) o;

		return Objects.equals(friends, that.friends);
	}

	@Generated
	@Override
	public int hashCode() {
		return friends != null ? friends.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "UserFriends[" +
				"friends=[" + friends + ']' +
				']';
	}

}
