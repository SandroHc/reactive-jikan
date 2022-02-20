/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.person;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.person.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the person manga-related positions.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getPersonManga">Jikan API docs - getPersonManga</a>
 */
public class PersonMangaQuery extends Query<DataListHolder<PersonRole>, Flux<PersonRole>> {

	/** The person ID. */
	protected final int id;

	public PersonMangaQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/people/" + id + "/manga");
	}

	@Override
	public Flux<PersonRole> process(Mono<DataListHolder<PersonRole>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
