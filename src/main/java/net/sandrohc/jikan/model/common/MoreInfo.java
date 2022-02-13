/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.sandrohc.jikan.utils.Generated;

/**
 * The 'more info' details for an anime/manga.
 */
public class MoreInfo implements Serializable {

	@JsonProperty("moreinfo")
	public String moreInfo;


	public String getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

	@Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MoreInfo moreInfo1 = (MoreInfo) o;

		return moreInfo != null ? moreInfo.equals(moreInfo1.moreInfo) : moreInfo1.moreInfo == null;
	}

	@Generated
	@Override
	public int hashCode() {
		return moreInfo != null ? moreInfo.hashCode() : 0;
	}

	@Generated
	@Override
	public String toString() {
		return "MoreInfo[moreInfo=" + moreInfo + ']';
	}
}
