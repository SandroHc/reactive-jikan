/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.random;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.manga.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for a random manga.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getRandomManga">Jikan API docs - getRandomManga</a>
 */
public class RandomMangaQuery extends Query<DataHolder<Manga>, Mono<Manga>> {

	public RandomMangaQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/random/manga");
	}

	@Override
	public Mono<Manga> process(Mono<DataHolder<Manga>> content) {
		return content.map(holder -> holder.data);
	}
}
