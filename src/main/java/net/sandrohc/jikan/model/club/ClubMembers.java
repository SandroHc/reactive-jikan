/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.club;

import java.util.*;

import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * Members list for a MAL club.
 */
public class ClubMembers extends CacheEntity {

	public List<ClubMember> members = Collections.emptyList();


	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ClubMembers that = (ClubMembers) o;

		return Objects.equals(members, that.members);
	}

	@Generated
	@Override
	public int hashCode() {
		return members != null ? members.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "ClubMembers[" +
				"members=[" + members + ']' +
				']';
	}

}
