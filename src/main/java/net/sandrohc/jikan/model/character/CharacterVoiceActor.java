/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.character;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.MalEntity;

public class CharacterVoiceActor extends MalEntity {

	public String name;

	public String url;

	@JsonProperty("image_url")
	public String imageUrl;

	public String language;


	@Override
	public String toString() {
		return "CharacterVoiceActor[id=" + malId + ", name='" + name + "']";
	}

}
