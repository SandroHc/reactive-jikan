/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;
import java.time.*;

import net.sandrohc.jikan.utils.Generated;

/**
 * A date range.
 */
public class DateRange implements Serializable {

	public OffsetDateTime from;
	public OffsetDateTime to;


	public OffsetDateTime getFrom() {
		return from;
	}

	public void setFrom(OffsetDateTime from) {
		this.from = from;
	}

	public OffsetDateTime getTo() {
		return to;
	}

	public void setTo(OffsetDateTime to) {
		this.to = to;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DateRange dateRange = (DateRange) o;

		if (from != null ? !from.equals(dateRange.from) : dateRange.from != null) return false;
		return to != null ? to.equals(dateRange.to) : dateRange.to == null;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = from != null ? from.hashCode() : 0;
		result = 31 * result + (to != null ? to.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "DateRange[from=" + from + ", to=" + to + ']';
	}

}
