/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.user;

import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.legacy.enums.*;

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
