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
import net.sandrohc.jikan.query.genre.GenreAnimeQuery;
import net.sandrohc.jikan.test.RequestTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeGenreQueryTest extends RequestTest {

	@Test
	void fetchAnimeGenre() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/genres/anime", "genres/getAnimeGenres.json");

		/* Act */
		GenreAnimeQuery query = jikan.query().anime().genres();
		Collection<EntityWithCount> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/genres/anime");

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
