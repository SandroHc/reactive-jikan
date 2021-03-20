/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.schedule;

import java.util.*;

import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.season.*;

/**
 * The base object for the schedule query.
 */
public class Schedule extends CacheEntity {

    /** The list of anime broadcasting on monday. */
    public List<SeasonAnime> monday;

    /** The list of anime broadcasting on tuesday. */
    public List<SeasonAnime> tuesday;

    /** The list of anime broadcasting on wednesday. */
    public List<SeasonAnime> wednesday;

    /** The list of anime broadcasting on thursday. */
    public List<SeasonAnime> thursday;

    /** The list of anime broadcasting on friday. */
    public List<SeasonAnime> friday;

    /** The list of anime broadcasting on saturday. */
    public List<SeasonAnime> saturday;

    /** The list of anime broadcasting on sunday. */
    public List<SeasonAnime> sunday;

    /** The list of anime broadcasting on irregular intervals. */
    public List<SeasonAnime> other;

    /** The list of anime broadcasting where the regularity is not known. */
    public List<SeasonAnime> unknown;


    @Override
    public String toString() {
        return "Schedule";
    }

}
