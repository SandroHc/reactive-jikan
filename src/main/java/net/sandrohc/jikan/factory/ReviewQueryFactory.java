/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.query.review.RecentAnimeReviewQuery;
import net.sandrohc.jikan.query.review.RecentMangaReviewQuery;
import net.sandrohc.jikan.query.review.ReviewTopQuery;

/**
 * Factory for the review queries.
 *
 * @see <a href="https://docs.api.jikan.moe/#tag/reviews">Jikan API docs - reviews</a>
 */
public class ReviewQueryFactory extends Factory {

    public ReviewQueryFactory(Jikan jikan) {
        super(jikan);
    }

    /**
     * Get a list of recent anime recommendations.
     *
     * @return The anime recommendations query
     * @see <a href="https://docs.api.jikan.moe/#operation/getRecentAnimeReviews">Jikan API docs - getRecentAnimeReviews</a>
     */
    public RecentAnimeReviewQuery anime() {
        return new RecentAnimeReviewQuery(this.jikan);
    }

    /**
     * Get a list of recent manga recommendations.
     *
     * @return The manga recommendations query
     * @see <a href="https://docs.api.jikan.moe/#operation/getRecentMangaReviews">Jikan API docs - getRecentMangaReviews</a>
     */
    public RecentMangaReviewQuery manga() {
        return new RecentMangaReviewQuery(this.jikan);
    }

    /**
     * Get the most popular review by number of favorites.
     *
     * @return The review top query
     * @see <a href="https://docs.api.jikan.moe/#operation/getTopReviews">Jikan API docs - getTopReviews</a>
     */
    public ReviewTopQuery top() {
        return new ReviewTopQuery(this.jikan);
    }
}
