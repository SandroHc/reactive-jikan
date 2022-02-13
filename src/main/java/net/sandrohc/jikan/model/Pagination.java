/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model;

import java.io.*;

import net.sandrohc.jikan.utils.Generated;

public class Pagination implements Serializable {

	public int lastVisiblePage;
	public boolean hasNextPage;


	public int getLastVisiblePage() {
		return lastVisiblePage;
	}

	public void setLastVisiblePage(int lastVisiblePage) {
		this.lastVisiblePage = lastVisiblePage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Pagination that = (Pagination) o;

		if (lastVisiblePage != that.lastVisiblePage) return false;
		return hasNextPage == that.hasNextPage;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = lastVisiblePage;
		result = 31 * result + (hasNextPage ? 1 : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "Pagination{" +
				"lastVisiblePage=" + lastVisiblePage +
				", hasNextPage=" + hasNextPage +
				'}';
	}
}
