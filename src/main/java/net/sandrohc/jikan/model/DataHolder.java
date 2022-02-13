/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model;

import java.io.*;

import net.sandrohc.jikan.utils.Generated;

public class DataHolder<T> implements Serializable {

	public T data;


	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DataHolder<?> that = (DataHolder<?>) o;

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
		return "DataHolder[data=" + data + ']';
	}
}
