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

public class SeasonUpcomingQueryTest extends QueryTest {

	@Test
	void fetchSeasonUpcoming() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/seasons/upcoming", "seasons/getSeasonUpcoming.json");

		/* Act */
		SeasonUpcomingQuery query = jikan.query().season().upcoming();
		Collection<Anime> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/seasons/upcoming");

		// Top
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		Anime result = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(result.toString()).isNotNull();
		softly.assertThat(result.malId).isEqualTo(44511);
		softly.assertThat(result.rank).isEqualTo(0);
		softly.assertThat(result.title).isEqualTo("Chainsaw Man");
		softly.assertThat(result.url).isEqualTo("https://myanimelist.net/anime/44511/Chainsaw_Man");
		softly.assertThat(result.images.jpg.imageUrl).isEqualTo("https://cdn.myanimelist.net/images/anime/1632/110707.jpg");
		softly.assertThat(result.type).isEqualTo(AnimeType.TV);
		softly.assertThat(result.episodes).isNull();
		softly.assertThat(result.aired.from).isEqualTo(LocalDate.of(2022, Month.JANUARY, 1).atTime(0, 0).atOffset(ZoneOffset.UTC));
		softly.assertThat(result.aired.to).isNull();
		softly.assertThat(result.members).isEqualTo(338455);
		softly.assertThat(result.score).isEqualTo(0.0D);
		softly.assertAll();
	}
}
