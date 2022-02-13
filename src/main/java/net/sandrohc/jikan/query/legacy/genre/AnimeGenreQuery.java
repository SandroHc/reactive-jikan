/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.genre;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.legacy.anime.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AnimeGenreQuery extends GenreQuery<AnimeGenreSub, AnimeGenre> {

	public AnimeGenreQuery(Jikan jikan, AnimeGenre genre, int page) throws JikanInvalidArgumentException {
		super(jikan, Type.ANIME, genre, page);
	}

	@Override
	public Class<AnimeGenreSub> getRequestClass() {
		return AnimeGenreSub.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return AnimeGenreList.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<AnimeGenreSub> process(Mono<?> content) {
		return ((Mono<AnimeGenreList>) content).flatMapMany(results -> Flux.fromIterable(results.anime));
	}

}
