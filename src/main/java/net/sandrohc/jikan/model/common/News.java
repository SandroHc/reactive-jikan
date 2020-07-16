/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.util.*;

import net.sandrohc.jikan.model.base.CacheEntity;

public class News extends CacheEntity {

	public List<NewsArticle> articles = Collections.emptyList();


	@Override
	public String toString() {
		return "News[articles" + articles.size() + " articles]";
	}

}
