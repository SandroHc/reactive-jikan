/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.user;

import java.io.*;
import java.time.*;

import com.fasterxml.jackson.annotation.JsonAlias;
import net.sandrohc.jikan.utils.Generated;

/**
 * A user update to an anime/manga entry.
 */
public class UserUpdate implements Serializable {

    /** The score given by the user, from 0.0 to 10.0. */
    public double score;

    /** The status, e.g. 'Watching', 'Reading'. */
    public String status;

    /** The number of episodes/chapters watched by the user. */
    @JsonAlias({ "episodes_seen", "chapters_read" })
    public int seen;

    /** The number of episodes/chapters for this anime/manga. */
    @JsonAlias({ "episodes_total", "chapters_total" })
    public int total;

    /** The number of volumes read by the user. Available only for manga. */
    @JsonAlias({ "volumes_read" })
    public int volumesRead;

    /** The number of volumes for this manga. Available only for manga. */
    @JsonAlias({ "volumes_total" })
    public int volumesTotal;

    public OffsetDateTime date;


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
        if (volumesRead != that.volumesRead) return false;
        if (volumesTotal != that.volumesTotal) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Generated
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(score);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + seen;
        result = 31 * result + total;
        result = 31 * result + volumesRead;
        result = 31 * result + volumesTotal;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Generated
    @Override
    public String toString() {
        return "UserUpdate[score=" + score + ", date=" + date + ']';
    }
}
