/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.query.anime.*;
import net.sandrohc.jikan.query.genre.AnimeGenreQuery;
import net.sandrohc.jikan.query.search.AnimeSearchQuery;
import net.sandrohc.jikan.query.top.AnimeTopQuery;

/**
 * Factory for the anime queries.
 */
public class AnimeQueryFactory extends Factory {

    public AnimeQueryFactory(Jikan jikan) {
        super(jikan);
    }

    public AnimeQuery get(int id) {
        return new AnimeQuery(this.jikan, id);
    }

    public AnimeSearchQuery search() {
        return new AnimeSearchQuery(this.jikan);
    }

    public AnimeTopQuery top(int page) throws JikanInvalidArgumentException {
        return new AnimeTopQuery(this.jikan, page);
    }

    public AnimeGenreQuery genre(AnimeGenre genre, int page) throws JikanInvalidArgumentException {
        return new AnimeGenreQuery(this.jikan, genre, page);
    }

    public AnimeCharactersAndStaffQuery charactersAndStaff(int id) {
        return new AnimeCharactersAndStaffQuery(this.jikan, id);
    }

    public AnimeEpisodesQuery episodes(int id, int page) throws JikanInvalidArgumentException {
        return new AnimeEpisodesQuery(this.jikan, id, page);
    }

    public AnimeNewsQuery news(int id) {
        return new AnimeNewsQuery(this.jikan, id);
    }

    public AnimePicturesQuery pictures(int id) {
        return new AnimePicturesQuery(this.jikan, id);
    }

    public AnimeVideosQuery videos(int id) {
        return new AnimeVideosQuery(this.jikan, id);
    }

    public AnimeStatsQuery stats(int id) {
        return new AnimeStatsQuery(this.jikan, id);
    }

    public AnimeForumQuery forum(int id) {
        return new AnimeForumQuery(this.jikan, id);
    }

    public AnimeMoreInfoQuery moreInfo(int id) {
        return new AnimeMoreInfoQuery(this.jikan, id);
    }

    public AnimeReviewsQuery reviews(int id, int page) throws JikanInvalidArgumentException {
        return new AnimeReviewsQuery(this.jikan, id, page);
    }

    public AnimeRecommendationsQuery recommendations(int id) {
        return new AnimeRecommendationsQuery(this.jikan, id);
    }

    public AnimeUserUpdatesQuery userUpdates(int id, int page) throws JikanInvalidArgumentException {
        return new AnimeUserUpdatesQuery(this.jikan, id, page);
    }

}
