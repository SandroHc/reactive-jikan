/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.season;

import java.util.*;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.season.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class SeasonListQueryTest extends QueryTest {

	@Test
	void fetchSeason() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/seasons", "seasons/getSeasonsList.json");

		/* Act */
		SeasonListQuery query = jikan.query().season().list();
		Collection<SeasonEntry> results = query.execute().collectList().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/seasons");

		// Top
		assertThat(results).isNotNull();
		assertThat(results).hasSize(1);

		SeasonEntry season = results.iterator().next();
		softly = new SoftAssertions();
		softly.assertThat(season.toString()).isNotNull();
		softly.assertThat(season.year).isEqualTo(2022);
		softly.assertThat(season.seasons).containsExactly(Season.WINTER, Season.SPRING, Season.SUMMER, Season.FALL);
		softly.assertAll();
	}
}
