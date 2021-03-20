/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import net.sandrohc.jikan.Jikan;

public class UserHistoryAnimeQuery extends UserHistoryQuery {

	public UserHistoryAnimeQuery(Jikan jikan, String username) {
		super(jikan, "anime", username);
	}

}
