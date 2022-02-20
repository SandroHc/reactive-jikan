/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.*;
import net.sandrohc.jikan.model.genre.*;
import net.sandrohc.jikan.query.genre.GenreAnimeQuery;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.mockserver.model.Parameter;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeGenreQueryTest extends QueryTest {

	@Test
	void fetchAnimeGenre() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/genres/anime", "genres/getAnimeGenres.json",
				Parameter.param("filter", "genres"));

		/* Act */
		GenreAnimeQuery query = jikan.query().anime().genres().type(GenreType.GENRES);
		Collection<EntityWithCount> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/genres/anime?filter=genres");

		// Results
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		EntityWithCount gender = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(gender.toString()).isNotNull();
		softly.assertThat(gender.malId).isEqualTo(1);
		softly.assertThat(gender.url).isEqualTo("https://myanimelist.net/anime/genre/1/Action");
		softly.assertThat(gender.name).isEqualTo("Action");
		softly.assertThat(gender.count).isEqualTo(4124);
		softly.assertAll();
	}
}
