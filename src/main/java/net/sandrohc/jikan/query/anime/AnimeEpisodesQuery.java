/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the list of episodes.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeEpisodes">Jikan API docs - getAnimeEpisodes</a>
 */
public class AnimeEpisodesQuery extends Query<DataListHolderWithPagination<AnimeEpisode>, Flux<AnimeEpisode>> {

	/** The anime ID. */
	private final int id;

	// TODO: validate javadoc by checking if max episodes per page has changed
	/** The page. Each page contains up to 100 episodes. */
	private final int page;

	public AnimeEpisodesQuery(Jikan jikan, int id, int page) throws JikanInvalidArgumentException {
		super(jikan);
		if (page < 1) throw new JikanInvalidArgumentException("page starts at index 1");

		this.id = id;
		this.page = page;
	}

	@Override
	public String getUrl() {
		return endpoint("/anime/" + id + "/episodes")
				.queryParam("page", page)
				.build();
	}

	@Override
	public TypeReference<DataListHolderWithPagination<AnimeEpisode>> getResponseType() {
		return new TypeReference<DataListHolderWithPagination<AnimeEpisode>>() { };
	}

	@Override
	public Flux<AnimeEpisode> process(Mono<DataListHolderWithPagination<AnimeEpisode>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}

}
