/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.user;

import java.time.Duration;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.user.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;

public class UserStatisticsQueryTest extends QueryTest {

	@Test
	void fetchUserStatistics() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/users/USER_NAME/statistics", "users/getUserStatistics.json");

		/* Act */
		UserStatisticsQuery query = jikan.query().user().statistics("USER_NAME");
		UserStatistics statistics = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/users/USER_NAME/statistics");

		// Statistics
		assertThat(statistics).isNotNull();
		assertThat(statistics.toString()).isNotNull();

		// Statistics - Anime
		softly = new SoftAssertions();
		softly.assertThat(statistics.anime.toString()).isNotNull();
		softly.assertThat(statistics.anime.daysWatched).isEqualTo(Duration.ofDays(594).plusHours(12));
		softly.assertThat(statistics.anime.meanScore).isEqualTo(3.62D);
		softly.assertThat(statistics.anime.watching).isEqualTo(4);
		softly.assertThat(statistics.anime.completed).isEqualTo(3211);
		softly.assertThat(statistics.anime.onHold).isEqualTo(16);
		softly.assertThat(statistics.anime.dropped).isEqualTo(1121);
		softly.assertThat(statistics.anime.planToWatch).isEqualTo(28);
		softly.assertThat(statistics.anime.totalEntries).isEqualTo(4380);
		softly.assertThat(statistics.anime.rewatched).isEqualTo(422);
		softly.assertThat(statistics.anime.episodesWatched).isEqualTo(34987);
		softly.assertAll();

		// Statistics - Manga
		softly = new SoftAssertions();
		softly.assertThat(statistics.manga.toString()).isNotNull();
		softly.assertThat(statistics.manga.daysRead).isEqualTo(Duration.ofDays(13).plusHours(9).plusMinutes(36));
		softly.assertThat(statistics.manga.meanScore).isEqualTo(7.11D);
		softly.assertThat(statistics.manga.reading).isEqualTo(4);
		softly.assertThat(statistics.manga.completed).isEqualTo(24);
		softly.assertThat(statistics.manga.onHold).isEqualTo(7);
		softly.assertThat(statistics.manga.dropped).isEqualTo(0);
		softly.assertThat(statistics.manga.planToRead).isEqualTo(3);
		softly.assertThat(statistics.manga.totalEntries).isEqualTo(38);
		softly.assertThat(statistics.manga.reread).isEqualTo(1);
		softly.assertThat(statistics.manga.chaptersRead).isEqualTo(1515);
		softly.assertThat(statistics.manga.volumesRead).isEqualTo(179);
		softly.assertAll();
	}
}
