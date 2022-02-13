/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.user;

import java.time.*;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The friend of a MAL user.
 */
public class UserFriend extends UserSimple {

	@JsonProperty("last_online")
	public OffsetDateTime lastOnline;

	@JsonProperty("friends_since")
	public OffsetDateTime friendsSince;

}
