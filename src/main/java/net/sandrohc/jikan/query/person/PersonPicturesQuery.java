/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.person;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonPicturesQuery extends Query<Picture, Flux<Picture>> {

	private final int id;

	public PersonPicturesQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public String getUri() {
		return "/person/" + id + "/pictures";
	}

	@Override
	public Class<Picture> getRequestClass() {
		return Picture.class;
	}

	@Override
	public Class<?> getInitialRequestClass() {
		return Pictures.class;
	}

	@SuppressWarnings({"unchecked", "RedundantCast"})
	@Override
	public Flux<Picture> process(Mono<?> content) {
		return ((Mono<Pictures>) content).flatMapMany(results -> Flux.fromIterable(results.pictures));
	}

}
