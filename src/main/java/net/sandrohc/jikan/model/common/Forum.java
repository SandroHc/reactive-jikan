/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.util.*;

import net.sandrohc.jikan.model.base.CacheEntity;

public class Forum extends CacheEntity {

	public List<ForumTopic> topics = Collections.emptyList();


	@Override
	public String toString() {
		return "Forum[topics=" + topics.size() + " topics]";
	}

}
