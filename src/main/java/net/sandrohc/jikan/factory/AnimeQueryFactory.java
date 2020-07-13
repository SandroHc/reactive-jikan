/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.query.anime.*;
import net.sandrohc.jikan.query.search.AnimeSearchQuery;

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

    public AnimeCharactersAndStaffQuery charactersAndStaff(int id) {
        return new AnimeCharactersAndStaffQuery(this.jikan, id);
    }

    public AnimeEpisodesQuery episodes(int id, int page) {
        return new AnimeEpisodesQuery(this.jikan, id, page);
    }

    public AnimeForumQuery forum(int id, int page) {
        return new AnimeForumQuery(this.jikan, id);
    }

    public AnimeMoreInfoQuery moreInfo(int id, int page) {
        return new AnimeMoreInfoQuery(this.jikan, id);
    }

    public AnimeNewsQuery news(int id) {
        return new AnimeNewsQuery(this.jikan, id);
    }

    public AnimePicturesQuery pictures(int id) {
        return new AnimePicturesQuery(this.jikan, id);
    }

    public AnimeStatsQuery stats(int id) {
        return new AnimeStatsQuery(this.jikan, id);
    }

    public AnimeVideosQuery videos(int id) {
        return new AnimeVideosQuery(this.jikan, id);
    }

}
