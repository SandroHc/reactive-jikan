/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model;

import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.enums.*;

public class Genre extends MalEntity {

	public Type type;

	public net.sandrohc.jikan.model.enums.Genre name;

	public String url;



	@Override
	public String toString() {
		return "Genre[" +
			   "id=" + malId +
			   ", genre=" + name +
			   ']';
	}

}
