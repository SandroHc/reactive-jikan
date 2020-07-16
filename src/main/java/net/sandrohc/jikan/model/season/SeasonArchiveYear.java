/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.season;

import java.util.*;

import net.sandrohc.jikan.model.enums.Season;

public class SeasonArchiveYear {

    public int year;

    public List<Season> seasons;


    @Override
    public String toString() {
        return "SeasonArchiveYear[year=" + year + ", seasons=" + seasons + ']';
    }

}
