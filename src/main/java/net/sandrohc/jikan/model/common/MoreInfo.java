/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.CacheEntity;

/**
 * The details for the anime/manga for 'more info'.
 */
public class MoreInfo extends CacheEntity {

	@JsonProperty("moreinfo")
	public String moreInfo;


	@Override
	public String toString() {
		return "AnimeMoreInfo[moreInfo=" + moreInfo + ']';
	}

}
