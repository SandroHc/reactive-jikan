/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import net.sandrohc.jikan.model.*;

public class ProducerWithCount extends EntityWithCount {

	@Override
	public String toString() {
		return "ProducerWithCount[id=" + malId + ", name='" + name + "', count=" + count + ']';
	}
}
