/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the anime forum posts.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeForum">Jikan API docs - getAnimeForum</a>
 */
public class AnimeForumQuery extends Query<DataListHolder<AnimeForumTopic>, Flux<AnimeForumTopic>> {

	/** The anime ID. */
	private final int id;

	/** The forum topic type. */
	private final ForumTopicType forumTopicType;


	public AnimeForumQuery(Jikan jikan, int id) {
		this(jikan, id, null);
	}

	public AnimeForumQuery(Jikan jikan, int id, ForumTopicType forumTopicType) {
		super(jikan);
		this.id = id;
		this.forumTopicType = forumTopicType;
	}

	@Override
	public String getUrl() {
		QueryUrlBuilder builder = endpoint("/anime/" + id + "/forum");
		if (forumTopicType != null) builder.queryParam("filter", forumTopicType.value);
		return builder.build();
	}

	@Override
	public TypeReference<DataListHolder<AnimeForumTopic>> getResponseType() {
		return new TypeReference<DataListHolder<AnimeForumTopic>>() { };
	}

	@Override
	public Flux<AnimeForumTopic> process(Mono<DataListHolder<AnimeForumTopic>> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.data));
	}
}
