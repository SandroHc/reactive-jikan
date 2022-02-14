/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.person;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.person.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the person manga-related positions.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getPersonManga">Jikan API docs - getPersonManga</a>
 */
public class PersonMangaQuery extends Query<DataListHolder<PersonMangaPosition>, Flux<PersonMangaPosition>> {

	/** The person ID. */
	protected final int id;

	public PersonMangaQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUrl() {
		return endpoint("/people/" + id + "/manga").build();
	}

	@Override
	public TypeReference<DataListHolder<PersonMangaPosition>> getResponseType() {
		return new TypeReference<DataListHolder<PersonMangaPosition>>() { };
	}

	@Override
	public Flux<PersonMangaPosition> process(Mono<DataListHolder<PersonMangaPosition>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
