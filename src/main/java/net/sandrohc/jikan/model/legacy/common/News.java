/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.common;

import java.util.*;

import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.legacy.base.CacheEntity;

/**
 * The base object for the anime/manga news query.
 */
public class News extends CacheEntity {

	/** The news articles. */
	public List<NewsArticle> articles = Collections.emptyList();


	@Override
	public String toString() {
		return "News[articles" + articles.size() + " articles]";
	}

}
