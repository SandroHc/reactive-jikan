/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.io.*;
import java.util.*;

import net.sandrohc.jikan.utils.Generated;

// TODO: split opening/ending strings into properly typed classes
public class AnimeThemes implements Serializable {

	/** The opening songs. */
	public Collection<String> openings;

	/** The ending songs. */
	public Collection<String> endings;


	public Collection<String> getOpenings() {
		return openings;
	}

	public void setOpenings(Collection<String> openings) {
		this.openings = openings;
	}

	public Collection<String> getEndings() {
		return endings;
	}

	public void setEndings(Collection<String> endings) {
		this.endings = endings;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AnimeThemes that = (AnimeThemes) o;

		if (openings != null ? !openings.equals(that.openings) : that.openings != null) return false;
		return endings != null ? endings.equals(that.endings) : that.endings == null;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = openings != null ? openings.hashCode() : 0;
		result = 31 * result + (endings != null ? endings.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "AnimeThemes[openings=" + openings + ", endings=" + endings + ']';
	}
}
