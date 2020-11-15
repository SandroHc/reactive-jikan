/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.query.magazine.MagazineQuery;
import net.sandrohc.jikan.query.producer.ProducerQuery;
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

    public ProducerQuery producer(int id, int page) throws JikanInvalidArgumentException {
        return new ProducerQuery(this.jikan, id, page);
    }

    public MagazineQuery magazine(int id, int page) throws JikanInvalidArgumentException {
        return new MagazineQuery(this.jikan, id, page);
    }

    public UserQueryFactory user(String username) {
        return new UserQueryFactory(this.jikan, username);
    }

}
