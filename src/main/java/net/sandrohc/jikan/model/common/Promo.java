/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;

import net.sandrohc.jikan.utils.Generated;

/**
 * A promotional video.
 */
public class Promo implements Serializable {

	/** The promotional video title. */
	public String title;

	/** The promotional video trailer. */
	public Trailer trailer;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Trailer getTrailer() {
		return trailer;
	}

	public void setTrailer(Trailer trailer) {
		this.trailer = trailer;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Promo that = (Promo) o;

		if (title != null ? !title.equals(that.title) : that.title != null) return false;
		return trailer != null ? trailer.equals(that.trailer) : that.trailer == null;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = title != null ? title.hashCode() : 0;
		result = 31 * result + (trailer != null ? trailer.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "AnimeVideosPromo[title='" + title + "']";
	}
}
