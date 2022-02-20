/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.magazine;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import net.sandrohc.jikan.query.QueryableQuery;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Query for the latest magazines.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getMagazines">Jikan API docs - getMagazines</a>
 */
public class MagazineQuery extends QueryableQuery<DataListHolderWithPagination<MagazineWithCount>, Flux<MagazineWithCount>, MagazineQuery> {

	public MagazineQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create().path("/magazines");
	}

	@Override
	public Flux<MagazineWithCount> process(Mono<DataListHolderWithPagination<MagazineWithCount>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
