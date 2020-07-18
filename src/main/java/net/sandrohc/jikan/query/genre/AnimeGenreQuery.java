/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.genre;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.model.enums.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AnimeGenreQuery extends GenreQuery<AnimeGenreQuery, AnimeGenreList, AnimeGenreSub, AnimeGenre> {

	public AnimeGenreQuery(Jikan jikan, AnimeGenre genre, int page) throws JikanInvalidArgumentException {
		super(jikan, Type.ANIME, genre, page);
	}

	@Override
	public Class<AnimeGenreList> getRequestClass() {
		return AnimeGenreList.class;
	}

	@Override
	public Flux<AnimeGenreSub> process(Mono<AnimeGenreList> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.anime));
	}

}
