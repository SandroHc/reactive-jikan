/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.schedule;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.schedule.*;
import net.sandrohc.jikan.query.Query;

public class ScheduleQuery extends Query<Schedule> {

	/** The day of the week. */
	private Day day;

	public ScheduleQuery(Jikan jikan) {
		super(jikan);
	}

	public ScheduleQuery day(Day day) {
		this.day = day;
		return this;
	}

	@Override
	public String getUri() {
		if (day != null) {
			return "/schedule/" + day.name().toLowerCase();
		} else {
			return "/schedule";
		}
	}

	@Override
	public Class<Schedule> getRequestClass() {
		return Schedule.class;
	}

}
