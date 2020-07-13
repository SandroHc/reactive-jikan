/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.base;

import java.io.*;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class MalEntity implements Serializable {

	@JsonProperty("mal_id")
	public int malId;



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MalEntity base = (MalEntity) o;

		return malId == base.malId;
	}

	@Override
	public int hashCode() {
		return 31 * malId;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[" +
			   "id=" + malId +
			   ']';
	}

}
