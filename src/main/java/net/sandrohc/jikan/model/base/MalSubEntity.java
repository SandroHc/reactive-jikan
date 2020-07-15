/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.base;

import net.sandrohc.jikan.model.enums.*;

public class MalSubEntity extends MalEntity {

	public Type type;

	public String name;

	public String url;


	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[id=" + malId + ", name='" + name + "']";
	}

}
