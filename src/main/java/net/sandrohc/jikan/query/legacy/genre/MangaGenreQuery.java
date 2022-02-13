/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.genre;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.legacy.enums.*;
import net.sandrohc.jikan.model.legacy.manga.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MangaGenreQuery extends GenreQuery<MangaGenreSub, MangaGenre> {

	public MangaGenreQuery(Jikan jikan, MangaGenre genre, int page) throws JikanInvalidArgumentException {
		super(jikan, Type.MANGA, genre, page);
	}

	@Override
	public Class<MangaGenreSub> getRequestClass() {
		return MangaGenreSub.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return MangaGenreList.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<MangaGenreSub> process(Mono<?> content) {
		return ((Mono<MangaGenreList>) content).flatMapMany(results -> Flux.fromIterable(results.manga));
	}

}
