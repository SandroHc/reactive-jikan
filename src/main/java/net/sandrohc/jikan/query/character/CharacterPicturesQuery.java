/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.character;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the character pictures.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getCharacterPictures">Jikan API docs - getCharacterPictures</a>
 */
public class CharacterPicturesQuery extends Query<DataListHolder<Images>, Flux<Images>> {

	/** The character ID. */
	protected final int id;

	public CharacterPicturesQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrlBuilder getUrl() {
		return create().path("/characters/" + id + "/pictures");
	}

	@Override
	public Flux<Images> process(Mono<DataListHolder<Images>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
