/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.user;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.legacy.user.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class UserHistoryQuery extends Query<UserHistory, Flux<UserHistory>> {

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
	public Class<UserHistory> getRequestClass() {
		return UserHistory.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return UserHistoryList.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<UserHistory> process(Mono<?> content) {
		return ((Mono<UserHistoryList>) content).flatMapMany(results -> Flux.fromIterable(results.history));
	}

}
