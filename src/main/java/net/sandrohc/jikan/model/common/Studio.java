/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import net.sandrohc.jikan.model.*;

public class Studio extends EntityWithType {

	@Override
	public String toString() {
		return "Studio[id=" + malId + ", name='" + name + "']";
	}
}
