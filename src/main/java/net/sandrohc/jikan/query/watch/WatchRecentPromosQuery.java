/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.watch;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.PageableQuery;
import net.sandrohc.jikan.query.QueryUrlBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static net.sandrohc.jikan.query.QueryUrlBuilder.create;

/**
 * Get the recently released promotions/trailers.
 *
 * @see <a href="https://docs.api.jikan.moe/#operation/getWatchRecentPromos">Jikan API docs - getWatchRecentPromos</a>
 */
public class WatchRecentPromosQuery extends PageableQuery<DataListHolderWithPagination<PromoWithEntry>, Flux<PromoWithEntry>, WatchRecentPromosQuery> {

	public WatchRecentPromosQuery(Jikan jikan) {
		super(jikan);
	}

	@Override
	public QueryUrlBuilder getInnerUrl() {
		return create().path("/watch/promos");
	}

	@Override
	public Flux<PromoWithEntry> process(Mono<DataListHolderWithPagination<PromoWithEntry>> content) {
		return content.flatMapMany(holder -> Flux.fromIterable(holder.data));
	}
}
