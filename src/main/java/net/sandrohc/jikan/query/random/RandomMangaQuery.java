/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.random;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.manga.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrl;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrl.endpoint;

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
	public QueryUrl getUrl() {
		return endpoint("/random/manga");
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
