/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.manga.*;
import net.sandrohc.jikan.query.QueryFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MangaCharactersQuery extends QueryFlux<MangaCharacters, MangaCharacter> {

	/** The manga ID. */
	private final int id;

	public MangaCharactersQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUri() {
		return "/manga/" + id + "/characters";
	}

	@Override
	public Class<MangaCharacters> getRequestClass() {
		return MangaCharacters.class;
	}

	@Override
	public Flux<MangaCharacter> process(Mono<MangaCharacters> content) {
		return content.flatMapMany(results -> Flux.fromIterable(results.characters));
	}

}
