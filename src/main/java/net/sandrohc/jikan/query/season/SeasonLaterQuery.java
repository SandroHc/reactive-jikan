/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.season;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.season.*;
import net.sandrohc.jikan.query.Query;

public class SeasonLaterQuery extends Query<SeasonList> {

	public SeasonLaterQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public String getUri() {
		return "/season/later";
	}

	@Override
	public Class<SeasonList> getRequestClass() {
		return SeasonList.class;
	}

}
