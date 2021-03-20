/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AnimeForumQuery extends Query<ForumTopic, Flux<ForumTopic>> {

	/** The anime ID. */
	private final int id;


	public AnimeForumQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUri() {
		return "/anime/" + id + "/forum";
	}

	@Override
	public Class<ForumTopic> getRequestClass() {
		return ForumTopic.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return Forum.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<ForumTopic> process(Mono<?> content) {
		return ((Mono<Forum>) content).flatMapMany(results -> Flux.fromIterable(results.topics));
	}

}
