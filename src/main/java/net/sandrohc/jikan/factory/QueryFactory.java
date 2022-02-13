/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;

/**
 * Factory for all the queries.
 */
public class QueryFactory extends Factory {

    public QueryFactory(Jikan jikan) {
        super(jikan);
    }

    /**
     * All anime-related queries.
     *
     * @return the anime query factory
     * @see <a href="https://docs.api.jikan.moe/#tag/anime">Jikan API docs - anime</a>
     */
    public AnimeQueryFactory anime() {
        return new AnimeQueryFactory(this.jikan);
    }

    /**
     * All character-related queries.
     *
     * @return the character query factory
     * @see <a href="https://docs.api.jikan.moe/#tag/characters">Jikan API docs - characters</a>
     */
    public CharacterQueryFactory character() {
        return new CharacterQueryFactory(this.jikan);
    }

    // TODO: implement club queries
//    /**
//     * All club-related queries.
//     *
//     * @return the club query factory
//     * @see <a href="https://docs.api.jikan.moe/#tag/clubs">Jikan API docs - clubs</a>
//     */
//    public ClubQueryFactory club() {
//        return new ClubQueryFactory(this.jikan);
//    }

    // TODO: implement genre queries
//    /**
//     * All genre-related queries.
//     *
//     * @return the genre query factory
//     * @see <a href="https://docs.api.jikan.moe/#tag/genres">Jikan API docs - genres</a>
//     */
//    public GenreQueryFactory genre() {
//        return new GenreQueryFactory(this.jikan);
//    }

    // TODO: implement magazine queries
//    /**
//     * All magazine-related queries.
//     *
//     * @return the magazine query factory
//     * @see <a href="https://docs.api.jikan.moe/#tag/magazines">Jikan API docs - magazines</a>
//     */
//    public MagazineQueryFactory magazine() {
//        return new MagazineQueryFactory(this.jikan);
//    }

    // TODO: implement manga queries
//    /**
//     * All manga-related queries.
//     *
//     * @return the manga query factory
//     * @see <a href="https://docs.api.jikan.moe/#tag/manga">Jikan API docs - manga</a>
//     */
//    public MangaQueryFactory manga() {
//        return new MangaQueryFactory(this.jikan);
//    }

    // TODO: implement person queries
//    /**
//     * All person-related queries.
//     *
//     * @return the person query factory
//     * @see <a href="https://docs.api.jikan.moe/#tag/people">Jikan API docs - people</a>
//     */
//    public PersonQueryFactory person() {
//        return new ClubQueryFactory(this.jikan);
//    }

    // TODO: implement producer queries
//    /**
//     * All producer-related queries.
//     *
//     * @return the producer query factory
//     * @see <a href="https://docs.api.jikan.moe/#tag/producers">Jikan API docs - producers</a>
//     */
//    public ClubQueryFactory producer() {
//        return new ClubQueryFactory(this.jikan);
//    }

    // TODO: implement random queries
//    /**
//     * All random-related queries.
//     *
//     * @return the random query factory
//     * @see <a href="https://docs.api.jikan.moe/#tag/random">Jikan API docs - random</a>
//     */
//    public RandomQueryFactory random() {
//        return new RandomQueryFactory(this.jikan);
//    }

    // TODO: implement recommendation queries
//    /**
//     * All recommendation-related queries.
//     *
//     * @return the recommendation query factory
//     * @see <a href="https://docs.api.jikan.moe/#tag/recommendations">Jikan API docs - recommendations</a>
//     */
//    public RecommendationQueryFactory recommendation() {
//        return new RecommendationQueryFactory(this.jikan);
//    }

    // TODO: implement review queries
//    /**
//     * All review-related queries.
//     *
//     * @return the review query factory
//     * @see <a href="https://docs.api.jikan.moe/#tag/reviews">Jikan API docs - reviews</a>
//     */
//    public ReviewQueryFactory review() {
//        return new ReviewQueryFactory(this.jikan);
//    }

    // TODO: implement schedule queries
//    /**
//     * All schedule-related queries.
//     *
//     * @return the schedule query factory
//     * @see <a href="https://docs.api.jikan.moe/#tag/schedules">Jikan API docs - schedules</a>
//     */
//    public ScheduleQueryFactory schedule() {
//        return new ScheduleQueryFactory(this.jikan);
//    }

    // TODO: implement user queries
//    /**
//     * All user-related queries.
//     *
//     * @return the user query factory
//     * @see <a href="https://docs.api.jikan.moe/#tag/users">Jikan API docs - users</a>
//     */
//    public UserQueryFactory user() {
//        return new UserQueryFactory(this.jikan);
//    }

    // TODO: implement season queries
//    /**
//     * All season-related queries.
//     *
//     * @return the season query factory
//     * @see <a href="https://docs.api.jikan.moe/#tag/seasons">Jikan API docs - seasons</a>
//     */
//    public SeaspmQueryFactory season() {
//        return new SeaspmQueryFactory(this.jikan);
//    }

    // TODO: implement top queries
//    /**
//     * All top-related queries.
//     *
//     * @return the top query factory
//     * @see <a href="https://docs.api.jikan.moe/#tag/top">Jikan API docs - top</a>
//     */
//    public ClubQueryFactory top() {
//        return new ClubQueryFactory(this.jikan);
//    }

    // TODO: implement watch queries
//    /**
//     * All watch-related queries.
//     *
//     * @return the watch query factory
//     * @see <a href="https://docs.api.jikan.moe/#tag/watch">Jikan API docs - watch</a>
//     */
//    public ClubQueryFactory watch() {
//        return new ClubQueryFactory(this.jikan);
//    }
}
