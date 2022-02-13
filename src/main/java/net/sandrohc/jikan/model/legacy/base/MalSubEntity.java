/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.base;

import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.enums.*;

/**
 * A MyAnimeList with a type, name and URL.
 */
public class MalSubEntity extends MalEntity {

	/** The type. */
	public Type type;

	/** The name. */
	public String name;

	/** The URL to MyAnimeList. */
	public String url;


	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[id=" + malId + ", name='" + name + "']";
	}

}
