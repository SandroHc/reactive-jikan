/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.query.watch.WatchPopularEpisodesQuery;
import net.sandrohc.jikan.query.watch.WatchPopularPromosQuery;
import net.sandrohc.jikan.query.watch.WatchRecentEpisodesQuery;
import net.sandrohc.jikan.query.watch.WatchRecentPromosQuery;

/**
 * Factory for the episode/promo watch queries.
 *
 * @see <a href="https://docs.api.jikan.moe/#tag/watch">Jikan API docs - watch</a>
 */
public class WatchQueryFactory extends Factory {

    public WatchQueryFactory(Jikan jikan) {
        super(jikan);
    }

    /**
     * Get the recently released episodes.
     *
     * @return The recent episodes query
     * @see <a href="https://docs.api.jikan.moe/#operation/getWatchRecentEpisodes">Jikan API docs - getWatchRecentEpisodes</a>
     */
    public WatchRecentEpisodesQuery recentEpisodes() {
        return new WatchRecentEpisodesQuery(this.jikan);
    }

    /**
     * Get the most popular episodes.
     *
     * @return The popular episodes query
     * @see <a href="https://docs.api.jikan.moe/#operation/getWatchPopularEpisodes">Jikan API docs - getWatchPopularEpisodes</a>
     */
    public WatchPopularEpisodesQuery popularEpisodes() {
        return new WatchPopularEpisodesQuery(this.jikan);
    }

    /**
     * Get the recently released promotions/trailers.
     *
     * @return The recent promotions query
     * @see <a href="https://docs.api.jikan.moe/#operation/getWatchRecentPromos">Jikan API docs - getWatchRecentPromos</a>
     */
    public WatchRecentPromosQuery recentPromotionals() {
        return new WatchRecentPromosQuery(this.jikan);
    }

    /**
     * Get the most popular promotions/trailers.
     *
     * @return The popular promotions query
     * @see <a href="https://docs.api.jikan.moe/#operation/getWatchPopularPromos">Jikan API docs - getWatchPopularPromos</a>
     */
    public WatchPopularPromosQuery popularPromotionals() {
        return new WatchPopularPromosQuery(this.jikan);
    }
}
