/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.manga;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.legacy.common.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MangaForumQuery extends Query<AnimeForumTopic, Flux<AnimeForumTopic>> {

	/** The manga ID. */
	private final int id;

	public MangaForumQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUri() {
		return "/manga/" + id + "/forum";
	}

	@Override
	public Class<AnimeForumTopic> getRequestClass() {
		return AnimeForumTopic.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return Forum.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<AnimeForumTopic> process(Mono<?> content) {
		return ((Mono<Forum>) content).flatMapMany(results -> Flux.fromIterable(results.topics));
	}

}
