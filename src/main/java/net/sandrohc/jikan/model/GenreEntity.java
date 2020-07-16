/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model;

import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.enums.*;

public class GenreEntity<T> extends MalEntity {

	public Type type;

	public T name;

	public String url;

	@Override
	public String toString() {
		return "Genre[id=" + malId + ", genre=" + name + ']';
	}

}
