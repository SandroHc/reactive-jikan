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

	public String large;

	public String small;


	@Override
	public String toString() {
		return "Picture[large='" + large + "', small='" + small + "']";
	}

}
