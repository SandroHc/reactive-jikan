/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import java.io.*;
import java.time.*;

import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * An entry in the user recent history.
 */
public class UserHistoryEntry implements Serializable {

	/** The entry. */
	public EntityWithType entry;

	/** The total number of episodes/chapters read. */
	public int increment;

	/** The date of the update. */
	public OffsetDateTime date;


	public EntityWithType getEntry() {
		return entry;
	}

	public void setEntry(EntityWithType entry) {
		this.entry = entry;
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

	public OffsetDateTime getDate() {
		return date;
	}

	public void setDate(OffsetDateTime date) {
		this.date = date;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserHistoryEntry that = (UserHistoryEntry) o;

		if (increment != that.increment) return false;
		if (entry != null ? !entry.equals(that.entry) : that.entry != null) return false;
		return date != null ? date.equals(that.date) : that.date == null;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = entry != null ? entry.hashCode() : 0;
		result = 31 * result + increment;
		result = 31 * result + (date != null ? date.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "UserHistoryEntry[entry=" + entry + ", increment='" + increment + ", date=" + date + ']';
	}
}
