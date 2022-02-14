/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.manga.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrl;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrl.endpoint;

/**
 * Query for the manga details.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMangaById">Jikan API docs - getMangaById</a>
 */
public class MangaQuery extends Query<DataHolder<Manga>, Mono<Manga>> {

	/** The manga ID. */
	private final int id;

	public MangaQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrl getUrl() {
		return endpoint("/manga/" + id);
	}

	@Override
	public TypeReference<DataHolder<Manga>> getResponseType() {
		return new TypeReference<DataHolder<Manga>>() { };
	}

	@Override
	public Mono<Manga> process(Mono<DataHolder<Manga>> content) {
		return content.map(holder -> holder.data);
	}
}
