/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the "more info" section.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMangaMoreInfo">Jikan API docs - getMangaMoreInfo</a>
 */
public class MangaMoreInfoQuery extends Query<DataHolder<MoreInfo>, Mono<MoreInfo>> {

	/** The manga ID. */
	private final int id;

	public MangaMoreInfoQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/manga/" + id + "/moreinfo");
	}

	@Override
	public Mono<MoreInfo> process(Mono<DataHolder<MoreInfo>> content) {
		return content.map(results -> results.data);
	}
}
