/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.*;

public class AnimeSearch extends CacheEntity {

	public List<AnimeSearchSub> results = Collections.emptyList();

	@JsonProperty("last_page")
	public int lastPage;


	@Override
	public String toString() {
		return "AnimeSearch[results=" + (results.size() + " results") + ", lastPage=" + lastPage + ']';
	}

}
