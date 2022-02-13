/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.season;

import java.io.*;
import java.util.*;

import net.sandrohc.jikan.model.legacy.enums.Season;

/**
 * A anime season year archive, with the list of seasons available for the year.
 */
public class SeasonArchiveYear implements Serializable {

    public int year;

    public List<Season> seasons;


    @Override
    public String toString() {
        return "SeasonArchiveYear[year=" + year + ", seasons=" + seasons + ']';
    }

}
