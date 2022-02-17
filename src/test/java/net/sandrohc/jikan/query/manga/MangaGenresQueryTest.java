/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.query.genre.GenreMangaQuery;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.MOCK_URL;
import static net.sandrohc.jikan.test.MockUtils.mock;
import static org.assertj.core.api.Assertions.assertThat;

public class MangaGenresQueryTest extends RequestTest {

	@Test
	void fetchMangaGenres() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mock(mockServer, "/genres/manga", 1, "genres/manga.json");

		/* Act */
		GenreMangaQuery query = jikan.query().manga().genres();
		Collection<EntityWithCount> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build().toString()).isEqualTo(MOCK_URL + "/genres/manga");

		// Results
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		EntityWithCount result = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(results).hasSize(1);
		softly.assertThat(result.toString()).isNotNull();
		softly.assertThat(result.malId).isEqualTo(118156);
		softly.assertThat(result.url).isEqualTo("https://myanimelist.net/manga/118156/Arata_Primal");
		softly.assertThat(result.name).isEqualTo("Arata Primal");
		softly.assertThat(result.count).isEqualTo(4);
		softly.assertAll();
	}
}
