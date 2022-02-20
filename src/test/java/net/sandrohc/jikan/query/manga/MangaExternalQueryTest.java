/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class MangaExternalQueryTest extends QueryTest {

	@Test
	void fetchMangaExternal() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/manga/11757/external", "manga/getMangaExternal.json");

		/* Act */
		MangaExternalQuery query = jikan.query().manga().external(11757);
		Collection<External> external = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/manga/11757/external");

		// External
		softly = new SoftAssertions();
		softly.assertThat(external).isNotNull();
		softly.assertThat(external).hasSize(1);
		softly.assertThat(external)
				.extracting(e -> e.url, e -> e.name)
				.containsExactlyInAnyOrder(
						tuple("http://shingeki.net/", "Official Site")
				);
		softly.assertAll();
	}
}
