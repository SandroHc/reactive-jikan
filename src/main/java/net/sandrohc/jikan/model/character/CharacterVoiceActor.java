/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.character;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.MalEntity;

/**
 * Details about the voice actor.
 */
public class CharacterVoiceActor extends MalEntity {

	/** The voice actor's name. */
	public String name;

	/** The URL to the voice actor's page on MyAnimeList. */
	public String url;

	@JsonProperty("image_url")
	public String imageUrl;

	/** The language performed by the voice actor. */
	public String language;


	@Override
	public String toString() {
		return "CharacterVoiceActor[id=" + malId + ", name='" + name + "']";
	}

}
