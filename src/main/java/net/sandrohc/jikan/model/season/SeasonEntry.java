/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.season;

import java.io.*;
import java.util.*;

import net.sandrohc.jikan.utils.Generated;

/**
 * Entry with a year and the list of available seasons for that year.
 */
public class SeasonEntry implements Serializable {

    /** The year. */
    public int year;

    /** The list of available seasons for the year. */
    public Collection<Season> seasons = Collections.emptyList();


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Collection<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(Collection<Season> seasons) {
        this.seasons = seasons;
    }

    @Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeasonEntry that = (SeasonEntry) o;

        if (year != that.year) return false;
        return seasons != null ? seasons.equals(that.seasons) : that.seasons == null;
    }

    @Generated
    @Override
    public int hashCode() {
        int result = year;
        result = 31 * result + (seasons != null ? seasons.hashCode() : 0);
        return result;
    }

    @Generated
    @Override
    public String toString() {
        return "SeasonEntry[year=" + year + ", seasons=" + seasons + ']';
    }
}
