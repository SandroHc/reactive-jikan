/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.user;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.legacy.user.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Mono;

public class UserProfileQuery extends Query<UserProfile, Mono<UserProfile>> {

	protected final String username;

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
