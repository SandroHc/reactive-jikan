/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.character;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.character.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the character voice actors.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getCharacterVoiceActors">Jikan API docs - getCharacterVoiceActors</a>
 */
public class CharacterVoiceActorsQuery extends Query<DataListHolder<CharacterVoiceActor>, Flux<CharacterVoiceActor>> {

	/** The character ID. */
	protected final int id;

	public CharacterVoiceActorsQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/characters/" + id + "/voices");
	}

	@Override
	public Flux<CharacterVoiceActor> process(Mono<DataListHolder<CharacterVoiceActor>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
