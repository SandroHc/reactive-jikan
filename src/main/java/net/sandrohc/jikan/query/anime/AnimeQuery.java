/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the anime details.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeById">Jikan API docs - getAnimeById</a>
 */
public class AnimeQuery extends Query<DataHolder<Anime>, Mono<Anime>> {

	/** The anime ID. */
	protected final int id;

	public AnimeQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/anime/" + id);
	}

	@Override
	public Mono<Anime> process(Mono<DataHolder<Anime>> content) {
		return content.map(holder -> holder.data);
	}
}
