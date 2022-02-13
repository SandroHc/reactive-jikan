/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.user;

import net.sandrohc.jikan.Jikan;

public class UserHistoryMangaQuery extends UserHistoryQuery {

	public UserHistoryMangaQuery(Jikan jikan, String username) {
		super(jikan, "manga", username);
	}

}
