/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.query.schedule.ScheduleQuery;

public class QueryFactory extends Factory {

    public QueryFactory(Jikan jikan) {
        super(jikan);
    }

    public AnimeQueryFactory anime() {
        return new AnimeQueryFactory(this.jikan);
    }

    public CharacterQueryFactory character() {
        return new CharacterQueryFactory(this.jikan);
    }

    public MangaQueryFactory manga() {
        return new MangaQueryFactory(this.jikan);
    }

    public PersonQueryFactory person() {
        return new PersonQueryFactory(this.jikan);
    }

    public SeasonQueryFactory season() {
        return new SeasonQueryFactory(this.jikan);
    }

    public ScheduleQuery schedule() {
        return new ScheduleQuery(this.jikan);
    }

}
