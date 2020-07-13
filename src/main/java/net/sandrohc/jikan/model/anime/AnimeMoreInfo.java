/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.anime;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.model.base.CacheEntity;

public class AnimeMoreInfo extends CacheEntity {

	@JsonProperty("moreinfo")
	public String moreInfo;


	@Override
	public String toString() {
		return "AnimeMoreInfo[" +
			   "moreInfo=" + moreInfo +
			   ']';
	}

}
