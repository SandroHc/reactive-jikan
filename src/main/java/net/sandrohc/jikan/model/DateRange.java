/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model;

import java.io.Serializable;
import java.time.*;
import java.util.*;

public class DateRange implements Serializable {

	public LocalDate from;
	public LocalDate to;


	public void setFrom(OffsetDateTime from) {
		this.from = from != null ? from.toLocalDate() : null;
	}

	public void setTo(OffsetDateTime to) {
		this.to = to != null ? to.toLocalDate() : null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DateRange dateRange = (DateRange) o;

		return Objects.equals(from, dateRange.from) &&
			   Objects.equals(to, dateRange.to);
	}

	@Override
	public int hashCode() {
		return Objects.hash(from, to);
	}

	@Override
	public String toString() {
		return "DateRange[from=" + from + ", to=" + to + ']';
	}

}
