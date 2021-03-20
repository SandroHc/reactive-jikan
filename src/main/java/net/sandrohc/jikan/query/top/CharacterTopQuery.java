/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.top;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.character.CharacterTop;
import net.sandrohc.jikan.query.character.CharacterTopSub;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CharacterTopQuery extends TopQuery<CharacterTopSub> {

	public CharacterTopQuery(Jikan jikan, int page) throws JikanInvalidArgumentException {
		super(jikan, Type.CHARACTER, page);
	}

	@Override
	public Class<CharacterTopSub> getRequestClass() {
		return CharacterTopSub.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return CharacterTop.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<CharacterTopSub> process(Mono<?> content) {
		return ((Mono<CharacterTop>) content).flatMapMany(results -> Flux.fromIterable(results.top));
	}

}
