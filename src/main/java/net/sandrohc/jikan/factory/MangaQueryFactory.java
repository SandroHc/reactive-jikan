/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.manga.*;

/**
 * Factory for the manga queries.
 *
 * @see <a href="https://docs.api.jikan.moe/#tag/manga">Jikan API docs - manga</a>
 */
public class MangaQueryFactory extends Factory {

    public MangaQueryFactory(Jikan jikan) {
        super(jikan);
    }

    /**
     * Get the manga details.
     *
     * @param mangaId The manga ID
     * @return The manga query factory
     * @see <a href="https://docs.api.jikan.moe/#operation/getMangaById">Jikan API docs - getMangaById</a>
     */
    public MangaQuery get(int mangaId) {
        return new MangaQuery(this.jikan, mangaId);
    }

    /**
     * Get the manga search.
     *
     * @return The manga search query factory
     * @see <a href="https://docs.api.jikan.moe/#operation/getMangaById">Jikan API docs - getMangaById</a>
     */
    public MangaSearchQuery search() {
        return new MangaSearchQuery(this.jikan);
    }

    // TODO: implement 'top' and 'genre' endpoints
//    public MangaTopQuery top(int page) throws JikanInvalidArgumentException {
//        return new MangaTopQuery(this.jikan, page);
//    }

//    public MangaGenreQuery genre(MangaGenre genre, int page) throws JikanInvalidArgumentException {
//        return new MangaGenreQuery(this.jikan, genre, page);
//    }

    /**
     * Get the manga characters.
     *
     * @param mangaId The manga ID
     * @return The manga characters query factory
     * @see <a href="https://docs.api.jikan.moe/#operation/getMangaCharacters">Jikan API docs - getMangaCharacters</a>
     */
    public MangaCharactersQuery characters(int mangaId) {
        return new MangaCharactersQuery(this.jikan, mangaId);
    }

    /**
     * Get the manga news.
     *
     * @param mangaId The manga ID
     * @param page The page
     * @return The manga news query factory
     * @see <a href="https://docs.api.jikan.moe/#operation/getMangaNews">Jikan API docs - getMangaNews</a>
     */
    public MangaNewsQuery news(int mangaId, int page) throws JikanInvalidArgumentException {
        return new MangaNewsQuery(this.jikan, mangaId, page);
    }

    /**
     * Get the manga forum posts/topics.
     *
     * @param mangaId The manga ID
     * @param forumTopicType The forum topic type
     * @return The manga forum topics query factory
     * @see <a href="https://docs.api.jikan.moe/#operation/getMangaTopics">Jikan API docs - getMangaTopics</a>
     */
    public MangaForumQuery forum(int mangaId, ForumTopicType forumTopicType) {
        return new MangaForumQuery(this.jikan, mangaId);
    }

    /**
     * Get the manga pictures.
     *
     * @param mangaId The manga ID
     * @return The manga pictures query factory
     * @see <a href="https://docs.api.jikan.moe/#operation/getMangaPictures">Jikan API docs - getMangaPictures</a>
     */
    public MangaPicturesQuery pictures(int mangaId) {
        return new MangaPicturesQuery(this.jikan, mangaId);
    }

    /**
     * Get the manga statistics.
     *
     * @param mangaId The manga ID
     * @return The manga statistics query factory
     * @see <a href="https://docs.api.jikan.moe/#operation/getMangaStatistics">Jikan API docs - getMangaStatistics</a>
     */
    public MangaStatisticsQuery statistics(int mangaId) {
        return new MangaStatisticsQuery(this.jikan, mangaId);
    }

    /**
     * Get the "more info" section.
     *
     * @param mangaId The manga ID
     * @return The manga more info query factory
     * @see <a href="https://docs.api.jikan.moe/#operation/getMangaMoreInfo">Jikan API docs - getMangaMoreInfo</a>
     */
    public MangaMoreInfoQuery moreInfo(int mangaId) {
        return new MangaMoreInfoQuery(this.jikan, mangaId);
    }

    /**
     * Get the manga recommendations.
     *
     * @param mangaId The manga ID
     * @return The manga recommendations query factory
     * @see <a href="https://docs.api.jikan.moe/#operation/getMangaRecommendations">Jikan API docs - getMangaRecommendations</a>
     */
    public MangaRecommendationsQuery recommendations(int mangaId) {
        return new MangaRecommendationsQuery(this.jikan, mangaId);
    }

    /**
     * Get the manga user updates.
     *
     * @param mangaId The manga ID
     * @return The manga user updates query factory
     * @see <a href="https://docs.api.jikan.moe/#operation/getMangaUserUpdates">Jikan API docs - getMangaUserUpdates</a>
     */
    public MangaUserUpdatesQuery userUpdates(int mangaId, int page) throws JikanInvalidArgumentException {
        return new MangaUserUpdatesQuery(this.jikan, mangaId, page);
    }

    /**
     * Get the manga reviews.
     *
     * @param mangaId The manga ID
     * @return The manga reviews query factory
     * @see <a href="https://docs.api.jikan.moe/#operation/getMangaReviews">Jikan API docs - getMangaReviews</a>
     */
    public MangaReviewsQuery reviews(int mangaId, int page) throws JikanInvalidArgumentException {
        return new MangaReviewsQuery(this.jikan, mangaId, page);
    }

    /**
     * Get the manga relations.
     *
     * @return The manga relations query factory
     * @see <a href="https://docs.api.jikan.moe/#operation/getMangaRelations">Jikan API docs - getMangaRelations</a>
     */
    public MangaRelationsQuery relations(int mangaId) {
        return new MangaRelationsQuery(this.jikan, mangaId);
    }

    /**
     * Get the manga external resources.
     *
     * @return The manga external query factory
     * @see <a href="https://docs.api.jikan.moe/#operation/getMangaExternal">Jikan API docs - getMangaExternal</a>
     */
    public MangaExternalQuery external(int mangaId) {
        return new MangaExternalQuery(this.jikan, mangaId);
    }
}
