/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.club;

import java.time.*;

import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;

/**
 * A club object.
 */
public class Club extends MalEntity {

	/** The URL to the club page on MyAnimeList. */
	public String url;

	/** The banner image. */
	public Images images;

	/** The club name. */
	public String name;

	/** The number of members. */
	public int members;

	/** The club category. */
	public ClubCategory category;

	/** The club founding date. */
	public OffsetDateTime created;

	/** The club access type, e.g. 'public' */
	public ClubType access;


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

	public int getMembers() {
		return members;
	}

	public void setMembers(int members) {
		this.members = members;
	}

	public ClubCategory getCategory() {
		return category;
	}

	public void setCategory(ClubCategory category) {
		this.category = category;
	}

	public OffsetDateTime getCreated() {
		return created;
	}

	public void setCreated(OffsetDateTime created) {
		this.created = created;
	}

	public ClubType getAccess() {
		return access;
	}

	public void setAccess(ClubType access) {
		this.access = access;
	}

	@Override
	public String toString() {
		return "Club[id=" + malId + ", name='" + name + "']";
	}
}
