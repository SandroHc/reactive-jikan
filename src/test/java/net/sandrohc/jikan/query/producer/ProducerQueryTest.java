/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.producer;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class ProducerQueryTest extends QueryTest {

	@Test
	void fetchProducers() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/producers", "producers/getProducers.json");

		/* Act */
		ProducerQuery query = jikan.query().producer().list();
		Collection<ProducerWithCount> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/producers");

		// Results
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		ProducerWithCount producer = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(producer.toString()).isNotNull();
		softly.assertThat(producer.malId).isEqualTo(1);
		softly.assertThat(producer.url).isEqualTo("https://myanimelist.net/anime/producer/1/Studio_Pierrot");
		softly.assertThat(producer.name).isEqualTo("Studio Pierrot");
		softly.assertThat(producer.count).isEqualTo(270);
		softly.assertAll();
	}
}
