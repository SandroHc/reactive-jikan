/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the anime forum posts.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeForum">Jikan API docs - getAnimeForum</a>
 */
public class AnimeForumQuery extends Query<DataListHolder<ForumTopic>, Flux<ForumTopic>> {

	/** The anime ID. */
	protected int id;

	/** The forum topic type. */
	protected ForumTopicType forumTopicType;


	public AnimeForumQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	public AnimeForumQuery type(ForumTopicType type) {
		this.forumTopicType = type;
		return this;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create()
				.path("/anime/" + id + "/forum")
				.param("filter", forumTopicType, ForumTopicType::getValue);
	}

	@Override
	public Flux<ForumTopic> process(Mono<DataListHolder<ForumTopic>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
