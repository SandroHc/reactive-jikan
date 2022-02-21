/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import net.sandrohc.jikan.model.*;

/**
 * A promotional video.
 */
public class PromoWithEntry extends Promo {

	/** The anime entry. */
	public EntityWithImage entry;


	public EntityWithImage getEntry() {
		return entry;
	}

	public void setEntry(EntityWithImage entry) {
		this.entry = entry;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		PromoWithEntry that = (PromoWithEntry) o;

		return entry != null ? entry.equals(that.entry) : that.entry == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (entry != null ? entry.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "PromoWithEntry[title='" + title + "', trailer=" + trailer + ", entry=" + entry + ']';
	}
}
