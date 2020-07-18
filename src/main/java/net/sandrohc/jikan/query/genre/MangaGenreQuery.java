/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.genre;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.manga.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MangaGenreQuery extends GenreQuery<MangaGenreQuery, MangaGenreList, MangaGenreSub, MangaGenre> {

	public MangaGenreQuery(Jikan jikan, MangaGenre genre, int page) throws JikanInvalidArgumentException {
		super(jikan, Type.MANGA, genre, page);
	}

	@Override
	public Class<MangaGenreList> getRequestClass() {
		return MangaGenreList.class;
	}

	@Override
	public Flux<MangaGenreSub> process(Mono<MangaGenreList> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.manga));
	}

}
