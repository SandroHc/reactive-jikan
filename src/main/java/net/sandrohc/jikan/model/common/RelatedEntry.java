/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.enums.*;

public class RelatedEntry extends MalEntity {

	/** The type, e.g. 'anime'. */
	public Type type;

	/** The name. */
	public String name;

	/** URL to the anime/manga page. */
	public String url;


	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "RelatedEntry[id=" + malId + ", name='" + name + "']";
	}
}
