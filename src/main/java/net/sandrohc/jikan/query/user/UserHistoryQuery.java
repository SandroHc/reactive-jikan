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

public abstract class UserHistoryQuery extends QueryFlux<UserHistoryList, UserHistory> {

	private final String type;

	/** The username. **/
	private final String username;


	public UserHistoryQuery(Jikan jikan, String type, String username) {
		super(jikan);
		this.type = type;
		this.username = username;
	}

	@Override
	public String getUri() {
		return "/user/" + username + "/history/" + type;
	}

	@Override
	public Class<UserHistoryList> getRequestClass() {
		return UserHistoryList.class;
	}

	@Override
	public Flux<UserHistory> process(Mono<UserHistoryList> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.history));
	}

}
