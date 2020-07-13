/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model;

import java.time.*;
import java.util.*;

public class Aired {

	public OffsetDateTime from;
	public OffsetDateTime to;



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Aired aired = (Aired) o;

		return Objects.equals(from, aired.from) &&
			   Objects.equals(to, aired.to);
	}

	@Override
	public int hashCode() {
		return Objects.hash(from, to);
	}

	@Override
	public String toString() {
		return "Aired[" +
			   "from=" + from +
			   ", to=" + to +
			   ']';
	}

}
