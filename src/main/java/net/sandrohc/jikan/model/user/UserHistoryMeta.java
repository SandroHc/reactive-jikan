/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.enums.*;

/**
 * The metadata for the history of recent updates of a MAL user.
 */
public class UserHistoryMeta extends MalEntity {

	public UserHistoryType type;

	public String name;

	public String url;


	@Override
	public String toString() {
		return "UserHistoryMeta[" +
				"id=" + malId +
				", name='" + name + '\'' +
				']';
	}
}
