/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.base;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A MyAnimeList entity with a name, URL, image and role.
 */
public class RoleSubEntity extends MalEntity {

	/** The name. */
	public String name;

	/** The URL to MyAnimeList. */
	public String url;

	@JsonProperty("image_url")
	public String imageUrl;

	public String role;


	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[id=" + malId + ", name='" + name + "', role='" + role + "']";
	}

}
