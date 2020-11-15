/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.QueryFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserHistoryMangaQuery extends UserHistoryQuery {

	public UserHistoryMangaQuery(Jikan jikan, String username) {
		super(jikan, "manga", username);
	}

}
