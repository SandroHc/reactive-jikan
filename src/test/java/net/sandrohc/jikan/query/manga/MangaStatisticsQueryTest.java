/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.manga;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class MangaStatisticsQueryTest extends QueryTest {

	@Test
	void fetchMangaStatistics() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/manga/23390/statistics", "manga/getMangaStatistics.json");

		/* Act */
		MangaStatisticsQuery query = jikan.query().manga().statistics(23390);
		Statistics statistics = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/manga/23390/statistics");

		// Statistics
		softly = new SoftAssertions();
		softly.assertThat(statistics).isNotNull();
		softly.assertThat(statistics.toString()).isNotNull();
		softly.assertThat(statistics.seeing).isEqualTo(240448);
		softly.assertThat(statistics.completed).isEqualTo(217974);
		softly.assertThat(statistics.onHold).isEqualTo(29066);
		softly.assertThat(statistics.dropped).isEqualTo(20591);
		softly.assertThat(statistics.planToSee).isEqualTo(47127);
		softly.assertThat(statistics.total).isEqualTo(555206);
		softly.assertThat(statistics.scores)
				.extracting(s -> s.score, s -> s.votes, s -> s.percentage)
				.containsExactly(
						tuple(1, 4533, 1.3D),
						tuple(2, 1178, 0.3D),
						tuple(3, 1695, 0.5D),
						tuple(4, 3483, 1.0D),
						tuple(5, 5335, 1.6D),
						tuple(6, 11456, 3.4D),
						tuple(7, 30102, 8.9D),
						tuple(8, 66678, 19.7D),
						tuple(9, 99556, 29.5D),
						tuple(10, 113950, 33.7D)
				);
		int totalScores = statistics.scores.stream().mapToInt(s -> s.votes).sum();
		softly.assertThat(statistics.total >= totalScores).isTrue();
		softly.assertAll();
	}
}
