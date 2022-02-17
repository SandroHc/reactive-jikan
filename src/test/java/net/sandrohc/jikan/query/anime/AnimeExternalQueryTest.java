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
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class AnimeExternalQueryTest extends RequestTest {

	@Test
	void fetchAnimeExternal() throws JikanUrlException, JikanQueryException {
		/* Arrange */
		mock(mockServer, "/anime/11757/external", 1, "anime/getAnimeExternal.json");

		/* Act */
		AnimeExternalQuery query = jikan.query().anime().external(11757);
		Collection<External> externals = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/anime/11757/external");

		// External
		softly = new SoftAssertions();
		softly.assertThat(externals).isNotNull();
		softly.assertThat(externals).hasSize(1);
		softly.assertThat(externals)
				.extracting(e -> e.name, e -> e.url)
				.containsExactly(
						tuple("NAME", "URL")
				);
		softly.assertAll();
	}
}
