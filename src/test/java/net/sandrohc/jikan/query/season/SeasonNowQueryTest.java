/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.season;

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

public class SeasonNowQueryTest extends QueryTest {

	@Test
	void fetchSeasonNow() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/seasons/now", "seasons/getSeasonNow.json");

		/* Act */
		SeasonNowQuery query = jikan.query().season().current();
		Collection<Anime> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/seasons/now");

		// Top
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		Anime result = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(result.toString()).isNotNull();
		softly.assertThat(result.malId).isEqualTo(48583);
		softly.assertThat(result.rank).isEqualTo(2);
		softly.assertThat(result.title).isEqualTo("Shingeki no Kyojin: The Final Season Part 2");
		softly.assertThat(result.url).isEqualTo("https://myanimelist.net/anime/48583/Shingeki_no_Kyojin__The_Final_Season_Part_2");
		softly.assertThat(result.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/1948/120625.jpg");
		softly.assertThat(result.type).isEqualTo(AnimeType.TV);
		softly.assertThat(result.episodes).isNull();
		softly.assertThat(result.aired.from).isEqualTo(LocalDate.of(2022, Month.JANUARY, 10).atTime(0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(result.aired.to).isNull();
		softly.assertThat(result.members).isEqualTo(666475);
		softly.assertThat(result.score).isEqualTo(9.15D);
		softly.assertAll();
	}
}
