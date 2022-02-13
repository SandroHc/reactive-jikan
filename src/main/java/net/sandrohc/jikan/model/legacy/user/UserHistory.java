/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.user;

import java.io.*;
import java.time.*;
import java.util.*;

import net.sandrohc.jikan.utils.Generated;

/**
 * The history of recent updates of a MAL user.
 */
public class UserHistory implements Serializable {

	public UserHistoryMeta meta;

	public int increment;

	public OffsetDateTime date;


	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserHistory that = (UserHistory) o;

		return Objects.equals(meta, that.meta);
	}

	@Generated
	@Override
	public int hashCode() {
		return meta != null ? meta.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "UserFriend[" +
				"meta=[" + meta + ']' +
				", increment='" + increment +
				", date=" + date +
				']';
	}
}
