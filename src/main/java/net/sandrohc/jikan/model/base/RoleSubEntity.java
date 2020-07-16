/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleSubEntity extends MalEntity {

	public String name;

	public String url;

	@JsonProperty("image_url")
	public String imageUrl;

	public String role; // TODO: convert to enum (Main, Support)


	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "[id=" + malId + ", name='" + name + "', role='" + role + "']";
	}

}
