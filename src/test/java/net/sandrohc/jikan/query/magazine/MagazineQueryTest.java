/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.magazine;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MagazineQueryTest extends RequestTest {

	@Test
	void fetchMagazines() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/magazines", 1, "magazines/getMagazines.json");

		/* Act */
		MagazineQuery query = jikan.query().magazine().list();
		List<EntityWithCount> magazines = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;
		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/magazines");

		/* Results */
		assertNotNull(magazines);
		assertThat(magazines)
				.extracting(m -> m.malId, m -> m.url, m -> m.name, m -> m.count)
				.containsExactly(
						tuple(1, "URL", "NAME", 2)
				);
	}
}
