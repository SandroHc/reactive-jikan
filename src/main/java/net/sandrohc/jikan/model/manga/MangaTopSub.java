/*
 * Copyright © 2020, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.model.manga;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A manga object, used by the /top endpoint.
 */
public class MangaTopSub extends MangaBase {

    public int rank;

    /** The publishing start date, in the format "Mon YYYY". i.e. "Jan 2020" */
    @JsonProperty("start_date")
    public String startDate;

    /** The publishing end date, in the format "Mon YYYY". i.e. "Jan 2020" */
    @JsonProperty("end_date")
    public String endDate;

}
