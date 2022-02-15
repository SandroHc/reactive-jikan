/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model;

import java.io.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.utils.Generated;

/**
 * A MyAnimeList entity with an ID.
 */
public abstract class MalEntity implements Serializable {

	/** The MyAnimeList ID. */
	@JsonProperty("mal_id")
	public int malId;


	public int getMalId() {
		return malId;
	}

	public void setMalId(int malId) {
		this.malId = malId;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MalEntity base = (MalEntity) o;

		return malId == base.malId;
	}

	@Generated
	@Override
	public int hashCode() {
		return 31 * malId;
	}

	@Generated
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[id=" + malId + ']';
	}
}
