/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.query.recommendation.RecentAnimeRecommendationQuery;
import net.sandrohc.jikan.query.recommendation.RecentMangaRecommendationQuery;

/**
 * Factory for the recommendations queries.
 *
 * @see <a href="https://docs.api.jikan.moe/#tag/recommendations">Jikan API docs - recommendations</a>
 */
public class RecommendationQueryFactory extends Factory {

    public RecommendationQueryFactory(Jikan jikan) {
        super(jikan);
    }

    /**
     * Get a list of recent anime recommendations.
     *
     * @return The anime recommendations query
     * @see <a href="https://docs.api.jikan.moe/#operation/getRecentAnimeRecommendations">Jikan API docs - getRecentAnimeRecommendations</a>
     */
    public RecentAnimeRecommendationQuery anime() {
        return new RecentAnimeRecommendationQuery(this.jikan);
    }

    /**
     * Get a list of recent manga recommendations.
     *
     * @return The manga recommendations query
     * @see <a href="https://docs.api.jikan.moe/#operation/getRecentMangaRecommendations">Jikan API docs - getRecentMangaRecommendations</a>
     */
    public RecentMangaRecommendationQuery manga() {
        return new RecentMangaRecommendationQuery(this.jikan);
    }
}
