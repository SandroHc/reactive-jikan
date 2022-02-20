/*
 * Copyright © 2022, Sandro Marques and the reactive-jikan contributors
 *
 * @author Sandro Marques <sandro123iv@gmail.com>
 */

package net.sandrohc.jikan.query.anime;

import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.exception.JikanUrlException;
import net.sandrohc.jikan.model.common.*;
import net.sandrohc.jikan.query.QueryTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static net.sandrohc.jikan.test.MockUtils.mockFromFile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class AnimeStatisticsQueryTest extends QueryTest {

	@Test
	void fetchAnimeStatistics() throws JikanQueryException, JikanUrlException {
		/* Arrange */
		mockFromFile(mockServer, "/anime/11757/statistics", "anime/getAnimeStatistics.json");

		/* Act */
		AnimeStatisticsQuery query = jikan.query().anime().statistics(11757);
		Statistics statistics = query.execute().block();

		/* Assert */
		SoftAssertions softly;

		// Query
		assertThat(query.toString()).isNotNull();
		assertThat(query.getUrl().build()).isEqualTo("/anime/11757/statistics");

		// Statistics
		softly = new SoftAssertions();
		softly.assertThat(statistics).isNotNull();
		softly.assertThat(statistics.toString()).isNotNull();
		softly.assertThat(statistics.seeing).isEqualTo(95611);
		softly.assertThat(statistics.completed).isEqualTo(2287389);
		softly.assertThat(statistics.onHold).isEqualTo(31504);
		softly.assertThat(statistics.dropped).isEqualTo(110880);
		softly.assertThat(statistics.planToSee).isEqualTo(143053);
		softly.assertThat(statistics.total).isEqualTo(2668437);
		softly.assertThat(statistics.scores)
				.extracting(s -> s.score, s -> s.votes, s -> s.percentage)
				.containsExactly(
						tuple(1, 27658, 1.5D),
						tuple(2, 29980, 1.6D),
						tuple(3, 53022, 2.8D),
						tuple(4, 99184, 5.2D),
						tuple(5, 153936, 8.1D),
						tuple(6, 235711, 12.5D),
						tuple(7, 377151, 19.9D),
						tuple(8, 367677, 19.4D),
						tuple(9, 272927, 14.4D),
						tuple(10, 274569, 14.5D)
				);
		int totalScores = statistics.scores.stream().mapToInt(s -> s.votes).sum();
		softly.assertThat(statistics.total >= totalScores).isTrue();
		softly.assertAll();
	}
}
