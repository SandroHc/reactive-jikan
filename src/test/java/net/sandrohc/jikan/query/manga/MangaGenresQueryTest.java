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
import net.sandrohc.jikan.model.genre.*;
import net.sandrohc.jikan.query.genre.GenreMangaQuery;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.mockserver.model.Parameter;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class MangaGenresQueryTest extends QueryTest {

	@Test
	void fetchMangaGenres() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/genres/manga", "genres/getMangaGenres.json",
				Parameter.param("filter", "genres"));

		/* Act */
		GenreMangaQuery query = jikan.query().manga().genres().type(GenreType.GENRES);
		Collection<EntityWithCount> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/genres/manga?filter=genres");

		// Results
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		EntityWithCount result = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(results).hasSize(1);
		softly.assertThat(result.toString()).isNotNull();
		softly.assertThat(result.malId).isEqualTo(1);
		softly.assertThat(result.url).isEqualTo("https://myanimelist.net/manga/genre/1/Action");
		softly.assertThat(result.name).isEqualTo("Action");
		softly.assertThat(result.count).isEqualTo(7835);
		softly.assertAll();
	}
}
