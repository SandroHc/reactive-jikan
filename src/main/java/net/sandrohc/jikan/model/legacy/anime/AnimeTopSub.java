/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.legacy.anime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An anime object, used by the /top endpoint.
 */
public class AnimeTopSub extends AnimeBase {

    public int rank;

    /** The airing start date, in the format "Mon YYYY". i.e. "Jan 2020" */
    @JsonProperty("start_date")
    public String startDate;

    /** The airing end date, in the format "Mon YYYY". i.e. "Jan 2020" */
    @JsonProperty("end_date")
    public String endDate;

}
