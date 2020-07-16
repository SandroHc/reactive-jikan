/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.season;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.season.*;
import net.sandrohc.jikan.query.Query;

public class SeasonQuery extends Query<SeasonList> {

	/** The season year. */
	private final int year;

	/** The season. */
	private final Season season;

	public SeasonQuery(Jikan jikan, int year, Season season) {
		super(jikan);
		this.year = year;
		this.season = season;
	}

	@Override
	public String getUri() {
		return "/season/" + year + "/" + season.name().toLowerCase();
	}

	@Override
	public Class<SeasonList> getRequestClass() {
		return SeasonList.class;
	}

}
