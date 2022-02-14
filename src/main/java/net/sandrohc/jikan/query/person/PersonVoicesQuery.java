/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.person;

import com.fasterxml.jackson.core.type.TypeReference;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.person.*;
import net.sandrohc.jikan.query.Query;
import net.sandrohc.jikan.query.QueryUrl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrl.endpoint;

/**
 * Query for the person voice actor roles.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getPersonVoices">Jikan API docs - getPersonVoices</a>
 */
public class PersonVoicesQuery extends Query<DataListHolder<PersonVoiceActingRole>, Flux<PersonVoiceActingRole>> {

	/** The person ID. */
	protected final int id;

	public PersonVoicesQuery(Jikan jikan, int id) {
		super(jikan);
		this.id = id;
	}

	@Override
	public QueryUrl getUrl() {
		return endpoint("/people/" + id + "/voices");
	}

	@Override
	public TypeReference<DataListHolder<PersonVoiceActingRole>> getResponseType() {
		return new TypeReference<DataListHolder<PersonVoiceActingRole>>() { };
	}

	@Override
	public Flux<PersonVoiceActingRole> process(Mono<DataListHolder<PersonVoiceActingRole>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}