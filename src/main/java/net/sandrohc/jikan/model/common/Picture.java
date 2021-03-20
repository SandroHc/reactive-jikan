/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;

/**
 * A picture, containing large and small variants.
 */
public class Picture implements Serializable {

	/** The URL for the large variant. */
	public String large;

	/** The URL for the small variant. Images are resized to a width of 225 pixels. */
	public String small;


	@Override
	public String toString() {
		return "Picture[large='" + large + "', small='" + small + "']";
	}

}
