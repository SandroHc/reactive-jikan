/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.user;

import java.io.*;
import java.time.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.legacy.base.*;
import net.sandrohc.jikan.model.legacy.enums.*;
import net.sandrohc.jikan.utils.Generated;

/**
 * The profile of a MAL user.
 */
public class UserProfile extends CacheEntity implements Serializable {

	/** The user ID. */
	@JsonProperty("user_id")
	public int userId;

	/** The user name. */
	public String username;

	/** The URL to the user's profile on MyAnimeList. */
	@JsonProperty("url")
	public String url;

	@JsonProperty("image_url")
	public String imageUrl;

	@JsonProperty("last_online")
	public OffsetDateTime lastOnline;

	public UserGender gender;

	public OffsetDateTime birthday;

	public String location;

	public OffsetDateTime joined;

	@JsonProperty("anime_stats")
	public UserAnimeStats animeStats;

	@JsonProperty("manga_stats")
	public UserMangaStats mangaStats;

	public UserFavorites favorites;

	public String about;


	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserProfile base = (UserProfile) o;

		return userId == base.userId;
	}

	@Generated
	@Override
	public int hashCode() {
		return 31 * userId;
	}

	@Override
	public String toString() {
		return "UserProfile[id=" + userId + ']';
	}

}
