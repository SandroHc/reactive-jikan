/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.legacy.user;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanInvalidArgumentException;
import net.sandrohc.jikan.model.legacy.enums.*;
import net.sandrohc.jikan.model.legacy.user.*;
import net.sandrohc.jikan.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserMangaQuery extends Query<UserManga, Flux<UserManga>> {

    /**
     * The username.
     **/
    private final String username;

    /**
     * The page.
     */
    private final int page;

    /**
     * The watching status.
     */
    private UserMangaReadingStatus status;


    public UserMangaQuery(Jikan jikan, String username, int page) {
        super(jikan);
        this.username = username;
        this.page = page;
        this.status = UserMangaReadingStatus.ALL;
    }

    public UserMangaQuery status(UserMangaReadingStatus status) throws JikanInvalidArgumentException {
        if (status == null)
            throw new JikanInvalidArgumentException("status must not be null");
        this.status = status;
        return this;
    }

    @Override
    public String getUri() {
        return "/user/" + username + "/mangalist/" + status.status + '/' + page;
    }

    @Override
    public Class<UserManga> getRequestClass() {
        return UserManga.class;
    }

    @Override
    public Class<?> getInitialRequestClass() {
        return UserMangaList.class;
    }

    @SuppressWarnings({"unchecked", "RedundantCast"})
    @Override
    public Flux<UserManga> process(Mono<?> content) {
        return ((Mono<UserMangaList>) content).flatMapMany(results -> Flux.fromIterable(results.manga));
    }

}
