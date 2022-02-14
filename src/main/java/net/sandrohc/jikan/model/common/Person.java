/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * The person details.
 */
public class Person extends EntityWithImage {

	@Generated
	@Override
	public String toString() {
		return "AnimePerson[id=" + malId + ", name='" + name + "']";
	}
}
