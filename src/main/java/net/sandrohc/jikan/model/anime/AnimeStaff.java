/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.io.*;
import java.util.*;

import net.sandrohc.jikan.model.person.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * A staff member.
 */
public class AnimeStaff implements Serializable {

	/** The person details. */
	public PersonSimple person;

	/** The staff positions, e.g. 'Director'. */
	public Collection<String> positions = Collections.emptyList();


	public PersonSimple getPerson() {
		return person;
	}

	public void setPerson(PersonSimple person) {
		this.person = person;
	}

	public Collection<String> getPositions() {
		return positions;
	}

	public void setPositions(Collection<String> positions) {
		this.positions = positions;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		AnimeStaff that = (AnimeStaff) o;

		if (person != null ? !person.equals(that.person) : that.person != null) return false;
		return positions != null ? positions.equals(that.positions) : that.positions == null;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (person != null ? person.hashCode() : 0);
		result = 31 * result + (positions != null ? positions.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "AnimeStaff[person=" + person + ", positions=" + positions + ']';
	}
}
