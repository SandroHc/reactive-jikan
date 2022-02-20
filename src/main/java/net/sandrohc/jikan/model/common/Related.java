/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;
import java.util.*;

import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * A list of related anime or manga.
 */
public class Related implements Serializable {

	/** The relation type, e.g. 'Side story'. */
	public RelatedType relation;

	/** The related entries. */
	public Collection<RelatedEntry> entry = Collections.emptyList();


	public RelatedType getRelation() {
		return relation;
	}

	public void setRelation(RelatedType relation) {
		this.relation = relation;
	}

	public Collection<RelatedEntry> getEntry() {
		return entry;
	}

	public void setEntry(Collection<RelatedEntry> entry) {
		this.entry = entry;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Related related = (Related) o;

		if (relation != related.relation) return false;
		return entry != null ? entry.equals(related.entry) : related.entry == null;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = relation != null ? relation.hashCode() : 0;
		result = 31 * result + (entry != null ? entry.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "Related{" +
				"relation=" + relation +
				", entry=" + entry +
				'}';
	}
}
