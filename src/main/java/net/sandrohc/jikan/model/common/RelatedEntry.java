/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import net.sandrohc.jikan.model.*;

public class RelatedEntry extends EntityWithType {

	@Override
	public String toString() {
		return "RelatedEntry[id=" + malId + ", name='" + name + "']";
	}
}
