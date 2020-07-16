/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.query.manga.*;
import net.sandrohc.jikan.query.search.MangaSearchQuery;

public class MangaQueryFactory extends Factory {

    public MangaQueryFactory(Jikan jikan) {
        super(jikan);
    }

    public MangaQuery get(int id) {
        return new MangaQuery(this.jikan, id);
    }

    public MangaSearchQuery search() {
        return new MangaSearchQuery(this.jikan);
    }

    public MangaCharactersQuery characters(int id) {
        return new MangaCharactersQuery(this.jikan, id);
    }

    public MangaNewsQuery news(int id) {
        return new MangaNewsQuery(this.jikan, id);
    }

    public MangaPicturesQuery pictures(int id) {
        return new MangaPicturesQuery(this.jikan, id);
    }

    public MangaStatsQuery stats(int id) {
        return new MangaStatsQuery(this.jikan, id);
    }

    public MangaForumQuery forum(int id) {
        return new MangaForumQuery(this.jikan, id);
    }

    public MangaMoreInfoQuery moreInfo(int id) {
        return new MangaMoreInfoQuery(this.jikan, id);
    }

    public MangaReviewsQuery reviews(int id, int page) {
        return new MangaReviewsQuery(this.jikan, id, page);
    }

    public MangaRecommendationsQuery recommendations(int id) {
        return new MangaRecommendationsQuery(this.jikan, id);
    }

    public MangaUserUpdatesQuery userUpdates(int id, int page) {
        return new MangaUserUpdatesQuery(this.jikan, id, page);
    }

}
