/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.schedule;

import java.util.*;

import net.sandrohc.jikan.model.base.*;
import net.sandrohc.jikan.model.season.*;

public class Schedule extends CacheEntity {

    public List<SeasonAnime> monday;

    public List<SeasonAnime> tuesday;

    public List<SeasonAnime> wednesday;

    public List<SeasonAnime> thursday;

    public List<SeasonAnime> friday;

    public List<SeasonAnime> saturday;

    public List<SeasonAnime> sunday;

    public List<SeasonAnime> other;

    public List<SeasonAnime> unknown;


    @Override
    public String toString() {
        return "Schedule";
    }

}
