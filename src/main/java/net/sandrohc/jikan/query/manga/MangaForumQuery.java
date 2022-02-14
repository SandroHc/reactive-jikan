/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the manga forum posts/topics.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMangaTopics">Jikan API docs - getMangaTopics</a>
 */
public class MangaForumQuery extends Query<DataListHolder<ForumTopic>, Flux<ForumTopic>> {

	/** The manga ID. */
	protected final int id;

	/** The forum topic type. */
	protected ForumTopicType type;


	public MangaForumQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	public MangaForumQuery type(ForumTopicType type) {
		this.type = type;
		return this;
	}

	@Override
	public String getUrl() {
		QueryUrlBuilder builder = endpoint("/manga/" + id + "/forum");
		if (type != null) builder.queryParam("filter", type.value);
		return builder.build();
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
