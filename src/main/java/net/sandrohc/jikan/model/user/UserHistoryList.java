/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import java.util.*;

import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * The history list of recent updates of a MAL user.
 */
public class UserHistoryList extends CacheEntity {

	public List<UserHistory> history = Collections.emptyList();


	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserHistoryList that = (UserHistoryList) o;

		return Objects.equals(history, that.history);
	}

	@Generated
	@Override
	public int hashCode() {
		return history != null ? history.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "UserHistoryList[" +
				"history=[" + history + ']' +
				']';
	}

}
