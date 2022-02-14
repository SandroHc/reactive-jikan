/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.character;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.character.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrl.endpoint;

/**
 * Query for the character appearances on manga.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getCharacterManga">Jikan API docs - getCharacterManga</a>
 */
public class CharacterMangaQuery extends Query<DataListHolder<CharacterManga>, Flux<CharacterManga>> {

	/** The character ID. */
	protected final int id;

	public CharacterMangaQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrl getUrl() {
		return endpoint("/characters/" + id + "/manga");
	}

	@Override
	public TypeReference<DataListHolder<CharacterManga>> getResponseType() {
		return new TypeReference<DataListHolder<CharacterManga>>() { };
	}

	@Override
	public Flux<CharacterManga> process(Mono<DataListHolder<CharacterManga>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
