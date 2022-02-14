/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrl.endpoint;

/**
 * Query for the anime forum posts.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeForum">Jikan API docs - getAnimeForum</a>
 */
public class AnimeForumQuery extends Query<DataListHolder<ForumTopic>, Flux<ForumTopic>> {

	/** The anime ID. */
	protected final int id;

	/** The forum topic type. */
	protected final ForumTopicType forumTopicType;


	public AnimeForumQuery(Jikan jikan, int id) {
		this(jikan, id, null);
	}

	public AnimeForumQuery(Jikan jikan, int id, ForumTopicType forumTopicType) {
		super(jikan);
		this.id = id;
		this.forumTopicType = forumTopicType;
	}

	@Override
	public QueryUrl getUrl() {
		return endpoint("/anime/" + id + "/forum")
				.param("filter", forumTopicType, ForumTopicType::getValue);
	}

	@Override
	public TypeReference<DataListHolder<ForumTopic>> getResponseType() {
		return new TypeReference<DataListHolder<ForumTopic>>() { };
	}

	@Override
	public Flux<ForumTopic> process(Mono<DataListHolder<ForumTopic>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
