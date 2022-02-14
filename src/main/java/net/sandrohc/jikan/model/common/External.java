/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;

import net.sandrohc.jikan.utils.Generated;

/**
 * An external resource.
 */
public class External implements Serializable {

	/** The name of the external resource. */
	public String name;

	/** The URL to the external resource. */
	public String url;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		External that = (External) o;

		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		return url != null ? url.equals(that.url) : that.url == null;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (url != null ? url.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "AnimeExternal{" +
				"name='" + name + '\'' +
				", url='" + url + '\'' +
				'}';
	}
}
