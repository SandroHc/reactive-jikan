/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the list of staff.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeStaff">Jikan API docs - getAnimeStaff</a>
 */
public class AnimeStaffQuery extends Query<DataListHolder<AnimeStaff>, Flux<AnimeStaff>> {

	/** The anime ID. */
	private final int id;

	public AnimeStaffQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUrl() {
		return endpoint("/anime/" + id + "/staff").build();
	}

	@Override
	public TypeReference<DataListHolder<AnimeStaff>> getResponseType() {
		return new TypeReference<DataListHolder<AnimeStaff>>() { };
	}

	@Override
	public Flux<AnimeStaff> process(Mono<DataListHolder<AnimeStaff>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
