/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import java.time.*;
import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.anime.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimeTopQueryTest extends QueryTest {

	@SuppressWarnings("SpellCheckingInspection")
	@Test
	void fetchAnimeTop() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/top/anime", "top/getTopAnime.json");

		/* Act */
		AnimeTopQuery query = jikan.query().anime().top();
		Collection<Anime> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/top/anime");

		// Top
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		Anime result = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(result.toString()).isNotNull();
		softly.assertThat(result.malId).isEqualTo(5114);
		softly.assertThat(result.rank).isEqualTo(1);
		softly.assertThat(result.title).isEqualTo("Fullmetal Alchemist: Brotherhood");
		softly.assertThat(result.url).isEqualTo("https://myanimelist.net/anime/5114/Fullmetal_Alchemist__Brotherhood");
		softly.assertThat(result.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/1223/96541.jpg");
		softly.assertThat(result.type).isEqualTo(AnimeType.TV);
		softly.assertThat(result.episodes).isEqualTo(64);
		softly.assertThat(result.aired.from).isEqualTo(LocalDate.of(2009, Month.APRIL, 5).atTime(0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(result.aired.to).isEqualTo(LocalDate.of(2010, Month.JULY, 4).atTime(0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(result.members).isEqualTo(2783236);
		softly.assertThat(result.score).isEqualTo(9.15D);
		softly.assertAll();
	}
}
