/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.factory;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.query.producer.ProducerQuery;

/**
 * Factory for the producer queries.
 *
 * @see <a href="https://docs.api.jikan.moe/#tag/producers">Jikan API docs - producers</a>
 */
public class ProducerQueryFactory extends Factory {

    public ProducerQueryFactory(Jikan jikan) {
        super(jikan);
    }

    /**
     * Get list of producers.
     *
     * @return The magazine query
     * @see <a href="https://docs.api.jikan.moe/#operation/getProducers">Jikan API docs - getProducers</a>
     */
    public ProducerQuery list() {
        return new ProducerQuery(this.jikan);
    }
}
