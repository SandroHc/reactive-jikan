/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.manga.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MangaCharactersQuery extends Query<MangaCharacter, Flux<MangaCharacter>> {

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
	public Class<MangaCharacter> getRequestClass() {
		return MangaCharacter.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return MangaCharacters.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<MangaCharacter> process(Mono<?> content) {
		return ((Mono<MangaCharacters>) content).flatMapMany(results -> Flux.fromIterable(results.characters));
	}

}
