/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;

import net.sandrohc.jikan.utils.Generated;

public class Images implements Serializable {

	public Image jpg;
	public Image webp;


	public String getPreferredImageUrl() {
		String preferredImageUrl = null;
		if (webp != null)
			preferredImageUrl = webp.getPreferredImageUrl();
		if (preferredImageUrl == null && jpg != null)
			preferredImageUrl = jpg.getPreferredImageUrl();
		return preferredImageUrl;
	}

	public Image getJpg() {
		return jpg;
	}

	public void setJpg(Image jpg) {
		this.jpg = jpg;
	}

	public Image getWebp() {
		return webp;
	}

	public void setWebp(Image webp) {
		this.webp = webp;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Images images = (Images) o;

		if (jpg != null ? !jpg.equals(images.jpg) : images.jpg != null) return false;
		return webp != null ? webp.equals(images.webp) : images.webp == null;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = jpg != null ? jpg.hashCode() : 0;
		result = 31 * result + (webp != null ? webp.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "Images[jpg=" + jpg + ", webp=" + webp + ']';
	}
}
