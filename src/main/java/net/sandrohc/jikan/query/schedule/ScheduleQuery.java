/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.schedule;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the currently airing anime/manga schedules.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getSchedules">Jikan API docs - getSchedules</a>
 */
public class ScheduleQuery extends PageableQuery<DataListHolderWithPagination<Anime>, Flux<Anime>, ScheduleQuery> {

	/** The day of the week. */
	protected DayOfWeek day;


	public ScheduleQuery(Jikan jikan) {
		super(jikan);
	}

	public ScheduleQuery day(DayOfWeek day) {
		this.day = day;
		return this;
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create()
				.path("/schedules")
				.param("filter", day, DayOfWeek::getSearch);
	}

	@Override
	public Flux<Anime> process(Mono<DataListHolderWithPagination<Anime>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
