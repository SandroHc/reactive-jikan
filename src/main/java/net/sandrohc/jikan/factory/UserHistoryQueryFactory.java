/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.query.user.UserHistoryQuery;

/**
 * Factory for the user history queries.
 *
 * @see <a href="https://docs.api.jikan.moe/#tag/getUserHistory">Jikan API docs - getUserHistory</a>
 */
public class UserHistoryQueryFactory extends Factory {

	/** The username. */
	protected String username;

	public UserHistoryQueryFactory(Jikan jikan, String username) {
		super(jikan);
		this.username = username;
	}

	/**
	 * Get the user anime history.
	 *
	 * @return The user anime history query
	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserHistory">Jikan API docs - getUserHistory</a>
	 */
	public UserHistoryQuery anime() {
		return new UserHistoryQuery(this.jikan, "anime", username);
	}

	/**
	 * Get the user manga history.
	 *
	 * @return The user manga history query
	 * @see <a href="https://docs.api.jikan.moe/#operation/getUserHistory">Jikan API docs - getUserHistory</a>
	 */
	public UserHistoryQuery manga() {
		return new UserHistoryQuery(this.jikan, "manga", username);
	}
}
