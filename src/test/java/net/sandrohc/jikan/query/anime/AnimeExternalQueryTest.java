/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

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

public class AnimeExternalQueryTest extends QueryTest {

	@Test
	void fetchAnimeExternal() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mockFromFile(mockServer, "/anime/11757/external", "anime/getAnimeExternal.json");

		/* Act */
		AnimeExternalQuery query = jikan.query().anime().external(11757);
		Collection<External> externals = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/anime/11757/external");

		// External
		softly = new SoftAssertions();
		softly.assertThat(externals).isNotNull();
		softly.assertThat(externals).hasSize(1);
		softly.assertThat(externals)
				.extracting(e -> e.name, e -> e.url)
				.containsExactlyInAnyOrder(
						tuple("Official Site", "http://www.swordart-online.net/")
				);
		softly.assertAll();
	}
}
