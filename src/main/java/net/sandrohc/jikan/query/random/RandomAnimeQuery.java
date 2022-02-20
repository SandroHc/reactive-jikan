/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.random;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for a random anime.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getRandomAnime">Jikan API docs - getRandomAnime</a>
 */
public class RandomAnimeQuery extends Query<DataHolder<Anime>, Mono<Anime>> {

	public RandomAnimeQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/random/anime");
	}

	@Override
	public Mono<Anime> process(Mono<DataHolder<Anime>> content) {
		return content.map(holder -> holder.data);
	}
}
