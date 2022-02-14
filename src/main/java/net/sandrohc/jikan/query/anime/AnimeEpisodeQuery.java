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
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.endpoint;

/**
 * Query for the anime episode details.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getAnimeEpisodeById">Jikan API docs - getAnimeEpisodeById</a>
 */
public class AnimeEpisodeQuery extends Query<DataHolder<AnimeEpisode>, Mono<AnimeEpisode>> {

	/** The anime ID. */
	protected final int id;

	/** The episode number. */
	protected final int episode;

	public AnimeEpisodeQuery(Jikan jikan, int id, int episode) throws JikanInvalidArgumentException {
		super(jikan);
		if (episode < 1) throw new JikanInvalidArgumentException("episode starts at index 1");

		this.id = id;
		this.episode = episode;
	}

	@Override
	public String getUrl() {
		return endpoint("/anime/" + id + "/episodes/" + episode).build();
	}

	@Override
	public TypeReference<DataHolder<AnimeEpisode>> getResponseType() {
		return new TypeReference<DataHolder<AnimeEpisode>>() { };
	}

	@Override
	public Mono<AnimeEpisode> process(Mono<DataHolder<AnimeEpisode>> content) {
		return content.map(holder -> holder.data);
	}
}
