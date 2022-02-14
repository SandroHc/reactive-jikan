/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.character;

import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * The character details.
 */
public class CharacterDetails extends MalEntity {

	/** The URL to MyAnimeList. */
	public String url;

	/** The character image. */
	public Images images;

	/** The character name. */
	public String name;


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Generated
	@Override
	public String toString() {
		return "AnimeCharacterDetails[id=" + malId + ", name='" + name + "']";
	}
}
