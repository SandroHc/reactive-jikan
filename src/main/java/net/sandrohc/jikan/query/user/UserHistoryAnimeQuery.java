/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import net.sandrohc.jikan.Jikan;

/**
 * Query for the user recent anime history.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getUserHistory">Jikan API docs - getUserHistory</a>
 */
public class UserHistoryAnimeQuery extends UserHistoryQuery {

	public UserHistoryAnimeQuery(Jikan jikan, String username) {
		super(jikan, "anime", username);
	}
}
