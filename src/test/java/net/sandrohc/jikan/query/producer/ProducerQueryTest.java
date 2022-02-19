/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.producer;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.query.producer.ProducerQuery;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class ProducerQueryTest extends RequestTest {

	@Test
	void fetchProducers() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/producers", 1, "producers/getProducers.json");

		/* Act */
		ProducerQuery query = jikan.query().producer().list();
		Collection<EntityWithCount> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/producers");

		// Results
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		EntityWithCount producer = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(producer.toString()).isNotNull();
		softly.assertThat(producer.malId).isEqualTo(11817);
		softly.assertThat(producer.url).isEqualTo("URL");
		softly.assertThat(producer.name).isEqualTo("Yoshitsugu Matsuoka");
		softly.assertThat(producer.count).isEqualTo(1);
		softly.assertAll();
	}
}
