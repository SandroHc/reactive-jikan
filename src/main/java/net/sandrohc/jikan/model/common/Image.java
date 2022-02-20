/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.utils.Generated;

public class Image implements Serializable {

	/** The image URL. */
	@JsonProperty("image_url")
	public String imageUrl;

	/** The image URL for the small variant. */
	@JsonProperty("small_image_url")
	public String smallImageUrl;

	/** The image URL for the medium variant. */
	@JsonProperty("medium_image_url")
	public String mediumImageUrl;

	/** The image URL for the large variant. */
	@JsonProperty("large_image_url")
	public String largeImageUrl;

	/** The image URL for the extra-large variant. */
	@JsonProperty("maximum_image_url")
	public String maximumImageUrl;


	public String getPreferredImageUrl() {
		if (this.maximumImageUrl != null) return this.maximumImageUrl;
		if (this.largeImageUrl != null) return this.largeImageUrl;
		if (this.mediumImageUrl != null) return this.mediumImageUrl;
		if (this.imageUrl != null) return this.imageUrl;
		if (this.smallImageUrl != null) return this.smallImageUrl;
		return null;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSmallImageUrl() {
		return smallImageUrl;
	}

	public void setSmallImageUrl(String smallImageUrl) {
		this.smallImageUrl = smallImageUrl;
	}

	public String getMediumImageUrl() {
		return mediumImageUrl;
	}

	public void setMediumImageUrl(String mediumImageUrl) {
		this.mediumImageUrl = mediumImageUrl;
	}

	public String getLargeImageUrl() {
		return largeImageUrl;
	}

	public void setLargeImageUrl(String largeImageUrl) {
		this.largeImageUrl = largeImageUrl;
	}

	public String getMaximumImageUrl() {
		return maximumImageUrl;
	}

	public void setMaximumImageUrl(String maximumImageUrl) {
		this.maximumImageUrl = maximumImageUrl;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Image image = (Image) o;

		if (imageUrl != null ? !imageUrl.equals(image.imageUrl) : image.imageUrl != null) return false;
		if (smallImageUrl != null ? !smallImageUrl.equals(image.smallImageUrl) : image.smallImageUrl != null)
			return false;
		if (mediumImageUrl != null ? !mediumImageUrl.equals(image.mediumImageUrl) : image.mediumImageUrl != null)
			return false;
		if (largeImageUrl != null ? !largeImageUrl.equals(image.largeImageUrl) : image.largeImageUrl != null)
			return false;
		return maximumImageUrl != null ? maximumImageUrl.equals(image.maximumImageUrl) : image.maximumImageUrl == null;
	}

	@Generated
	@Override
	public int hashCode() {
		int result = imageUrl != null ? imageUrl.hashCode() : 0;
		result = 31 * result + (smallImageUrl != null ? smallImageUrl.hashCode() : 0);
		result = 31 * result + (mediumImageUrl != null ? mediumImageUrl.hashCode() : 0);
		result = 31 * result + (largeImageUrl != null ? largeImageUrl.hashCode() : 0);
		result = 31 * result + (maximumImageUrl != null ? maximumImageUrl.hashCode() : 0);
		return result;
	}

	@Generated
	@Override
	public String toString() {
		return "Image[" +
				"imageUrl='" + imageUrl + '\'' +
				", smallImageUrl='" + smallImageUrl + '\'' +
				", mediumImageUrl='" + mediumImageUrl + '\'' +
				", largeImageUrl='" + largeImageUrl + '\'' +
				", maximumImageUrl='" + maximumImageUrl + '\'' +
				']';
	}

}
