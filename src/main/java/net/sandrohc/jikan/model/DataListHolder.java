/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model;

import java.io.*;
import java.util.*;

import net.sandrohc.jikan.utils.Generated;

public class DataListHolder<T> implements Serializable {

	public Collection<T> data = Collections.emptyList();


	public Collection<T> getData() {
		return data;
	}

	public void setData(Collection<T> data) {
		this.data = data;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DataListHolder<?> that = (DataListHolder<?>) o;

		return data != null ? data.equals(that.data) : that.data == null;
	}

	@Generated
	@Override
	public int hashCode() {
		return data != null ? data.hashCode() : 0;
	}

	@Generated
	@Override
	public String toString() {
		return "DataListHolder[data=" + data + ']';
	}
}
