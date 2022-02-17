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
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the "more info" section.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeMoreInfo">Jikan API docs - getAnimeMoreInfo</a>
 */
// TODO: check if response matches DTO - https://api.jikan.moe/v4/anime/1337/moreinfo
public class AnimeMoreInfoQuery extends Query<DataHolder<MoreInfo>, Mono<MoreInfo>> {

	/** The anime ID. */
	protected final int id;

	public AnimeMoreInfoQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return endpoint("/anime/" + id + "/moreinfo");
	}

	@Override
	public TypeReference<DataHolder<MoreInfo>> getResponseType() {
		return new TypeReference<DataHolder<MoreInfo>>() { };
	}

	@Override
	public Mono<MoreInfo> process(Mono<DataHolder<MoreInfo>> content) {
		return content.map(results -> results.data);
	}
}
