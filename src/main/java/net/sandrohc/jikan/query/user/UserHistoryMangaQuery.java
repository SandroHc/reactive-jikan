/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import net.sandrohc.jikan.Jikan;

/**
 * Query for the user recent manga history.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getUserHistory">Jikan API docs - getUserHistory</a>
 */
public class UserHistoryMangaQuery extends UserHistoryQuery {

	public UserHistoryMangaQuery(Jikan jikan, String username) {
		super(jikan, "manga", username);
	}
}
