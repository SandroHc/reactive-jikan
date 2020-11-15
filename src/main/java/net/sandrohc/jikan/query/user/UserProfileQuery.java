/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.QueryMono;

public class UserProfileQuery extends QueryMono<UserProfile> {

	private final String username;

	public UserProfileQuery(Jikan jikan, String username) {
		super(jikan);
		this.username = username;
	}

	@Override
	public String getUri() {
		return "/user/" + username;
	}

	@Override
	public Class<UserProfile> getRequestClass() {
		return UserProfile.class;
	}

}
