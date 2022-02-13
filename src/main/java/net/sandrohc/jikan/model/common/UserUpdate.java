/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.common;

import java.io.*;
import java.time.*;

import com.fasterxml.jackson.annotation.JsonAlias;
import net.sandrohc.jikan.utils.Generated;

/**
 * A user update to an anime/manga entry.
 */
public class UserUpdate implements Serializable {

    /** The user details. */
    public User user;

    /** The score given by the user, from 0.0 to 10.0. */
    public double score;

    /** The status, e.g. 'Watching'. */
    public String status; // TODO: convert to enum

    @JsonAlias({ "episodes_seen", "chapters_seen" })
    public int seen;

    @JsonAlias({ "episodes_total", "chapters_total" })
    public int total;

    public OffsetDateTime date;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserUpdate that = (UserUpdate) o;

        if (Double.compare(that.score, score) != 0) return false;
        if (seen != that.seen) return false;
        if (total != that.total) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Generated
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = user != null ? user.hashCode() : 0;
        temp = Double.doubleToLongBits(score);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + seen;
        result = 31 * result + total;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Generated
    @Override
    public String toString() {
        return "UserUpdate[user='" + user + "', date=" + date + ']';
    }
}
