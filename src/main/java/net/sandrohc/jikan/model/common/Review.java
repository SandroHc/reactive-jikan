/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.time.*;

import com.fasterxml.jackson.annotation.JsonAlias;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.enums.*;
import net.sandrohc.jikan.model.user.*;

/**
 * An anime or manga review.
 */
public class Review extends MalEntity {

    /** The URL to the review on MyAnimeList. */
    public String url;

    /** The type of the review, if for an anime or a manga. */
    public Type type;

    /** The number of votes the review received. */
    public int votes;

    /** The date the review was posted. */
    public OffsetDateTime date;

    /** The review. */
    public String review;

    /** The number of episodes/chapters the reviewer watched. */
    @JsonAlias({ "episodes_watched", "chapters_read" })
    public int seen;

    /** The scores given by the reviewer. */
    public ReviewScores scores;

    /** The entry. Omitted if retrieving reviews for a specific anime or manga. */
    public EntityWithImage entry;

    /** The reviewer details. */
    public UserSimple user;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public ReviewScores getScores() {
        return scores;
    }

    public void setScores(ReviewScores scores) {
        this.scores = scores;
    }

    public UserSimple getUser() {
        return user;
    }

    public void setUser(UserSimple user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Review[id=" + malId + ", reviewer=" + user + ", votes=" + votes + ", date=" + date + ']';
    }
}
